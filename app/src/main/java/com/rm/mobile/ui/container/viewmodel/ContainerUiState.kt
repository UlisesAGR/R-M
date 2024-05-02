package com.rm.mobile.ui.container.viewmodel

sealed class ContainerUiState {
    internal data object CallPhone : ContainerUiState()

    internal data class RecognizeSpeech(
        val text: String,
    ) : ContainerUiState()

    internal data object OpenRecognizeSpeech : ContainerUiState()
}
