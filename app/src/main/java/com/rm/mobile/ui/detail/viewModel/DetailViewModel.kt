package com.rm.mobile.ui.detail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rm.mobile.data.network.utils.parseError
import com.rm.mobile.domain.provider.ResourceProvider
import com.rm.mobile.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {
    private val _detail: MutableSharedFlow<DetailUiState> = MutableSharedFlow()
    val detail: SharedFlow<DetailUiState> = _detail.asSharedFlow()

    fun getCharacter(id: Int) = viewModelScope.launch {
        _detail.emit(DetailUiState.Loading(true))
        getCharacterUseCase.invoke(id)
            .catch { exception ->
                _detail.emit(DetailUiState.Error(exception.parseError().errorMessage))
            }
            .collect { response ->
                if (response.isSuccessful()) {
                    response.data?.let { character ->
                        _detail.apply {
                            emit(DetailUiState.Loading(false))
                            emit(DetailUiState.ShowDetail(character))
                        }
                    }
                } else {
                    _detail.apply {
                        emit(DetailUiState.Loading(false))
                        emit(
                            DetailUiState.Error(
                                response.details
                                    ?: resourceProvider.getErrorGettingCharacterLabel()
                            )
                        )
                    }
                }
            }
    }
}
