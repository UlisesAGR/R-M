package com.rm.mobile.ui.container.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.rm.mobile.data.local.Database
import com.rm.mobile.data.network.service.CharacterServices
import com.rm.mobile.data.repository.CharactersRepository
import com.rm.mobile.domain.model.toDomain
import com.rm.mobile.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContainerViewModel @Inject constructor(
    private val database: Database,
    private val services: CharacterServices,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _container: MutableSharedFlow<ContainerUiState> = MutableSharedFlow()
    val container: SharedFlow<ContainerUiState> = _container.asSharedFlow()

    @OptIn(ExperimentalPagingApi::class)
    fun getCharacters() = Pager(
        config = PagingConfig(
            pageSize = Constants.PAGE_SIZE,
        ),
        pagingSourceFactory = {
            database.characterDao().getCharacters()
        },
        remoteMediator = CharactersRepository(
            database,
            services,
        )
    ).flow
        .map { pagingData -> pagingData.map { it.toDomain() } }
        .cachedIn(viewModelScope)
        .flowOn(dispatcher)

    fun openCallPhone() = viewModelScope.launch {
        _container.emit(ContainerUiState.CallPhone)
    }

    fun openRecognizeSpeech() = viewModelScope.launch {
        _container.emit(ContainerUiState.OpenRecognizeSpeech)
    }

    fun recognizeSpeech(text: String) = viewModelScope.launch {
        _container.emit(ContainerUiState.RecognizeSpeech(text))
    }
}
