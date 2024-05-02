package com.rm.mobile.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rm.mobile.data.local.Database
import com.rm.mobile.data.local.entity.CharacterEntity
import com.rm.mobile.data.local.entity.RemoteKeyEntity
import com.rm.mobile.data.network.service.CharacterServices
import com.rm.mobile.domain.model.toEntity
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class CharactersRepository(
    private val database: Database,
    private val services: CharacterServices,
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        return if (System.currentTimeMillis() - (database.remoteKeysDao().getCreationTime() ?: 0) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = services.getCharacters(page)

            val movies = apiResponse.results
            val endOfPaginationReached = movies.isEmpty()

            database.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().deleteAllRemoteKeys()
                    database.characterDao().deleteCharacters()
                }

                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = movies.map {
                    RemoteKeyEntity(id = it.id, prevKey = prevKey, currentPage = page, nextKey = nextKey)
                }

                database.remoteKeysDao().addAllRemoteKeys(remoteKeys)
                database.characterDao().addCharacters(movies.map { it.toEntity() })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterEntity>,
    ): RemoteKeyEntity? = state.anchorPosition?.let { position ->
        state.closestItemToPosition(position)?.id?.let { id ->
            database.remoteKeysDao().getRemoteKeysById(id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterEntity>,
    ): RemoteKeyEntity? = state.pages.firstOrNull {
        it.data.isNotEmpty()
    }?.data?.firstOrNull()?.let { movie ->
        database.remoteKeysDao().getRemoteKeysById(movie.id)
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterEntity>,
    ): RemoteKeyEntity? = state.pages.lastOrNull {
        it.data.isNotEmpty()
    }?.data?.lastOrNull()?.let { movie ->
        database.remoteKeysDao().getRemoteKeysById(movie.id)
    }
}
