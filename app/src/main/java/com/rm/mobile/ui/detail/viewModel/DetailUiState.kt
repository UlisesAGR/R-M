package com.rm.mobile.ui.detail.viewModel

import com.rm.mobile.domain.model.Character

sealed class DetailUiState {
    internal data class Loading(
        val isLoading: Boolean,
    ) : DetailUiState()

    internal data class ShowDetail(
        val character: Character,
    ) : DetailUiState()

    internal data class Error(
        val message: String,
    ) : DetailUiState()
}
