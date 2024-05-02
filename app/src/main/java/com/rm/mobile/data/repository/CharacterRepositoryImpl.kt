/*
 * ContainerRepositoryImpl.kt
 * Created by Ulises Gonzalez on 02/05/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.data.repository

import com.rm.mobile.data.source.CharacterDataSource
import com.rm.mobile.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource,
    private val dispatcher: CoroutineDispatcher,
) : CharacterRepository {
    override suspend fun getCharacter(id: Int) =
        flow {
            emit(characterDataSource.getCharacter(id))
        }.flowOn(dispatcher)
}
