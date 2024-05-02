package com.rm.mobile.ui.container.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rm.mobile.R
import com.rm.mobile.databinding.FragmentNoteBinding
import com.rm.mobile.greenbox.banner.BannerType
import com.rm.mobile.ui.container.viewmodel.ContainerUiState
import com.rm.mobile.ui.container.viewmodel.ContainerViewModel
import com.rm.mobile.utils.collect
import com.rm.mobile.utils.setOnSafeClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentNoteBinding.inflate(layoutInflater)
    }

    private val containerViewModel: ContainerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setInitUi()
    }

    private fun setInitUi() {
        showBanner()
        setListeners()
        setFlows()
    }

    private fun showBanner() {
        binding.banner.show(
            style = BannerType.SUCCESS,
            title = getString(R.string.app_message),
            subTitle = getString(R.string.app_update_your_note_from_here),
            hasStatusIcon = true,
            hasCloseActionButton = true,
            closeAction = {},
        )
    }

    private fun setListeners() = with(binding) {
        recognizeSpeechButton.setOnSafeClickListener {
            clearNote()
            containerViewModel.openRecognizeSpeech()
        }
        clearButton.setOnClickListener {
            clearNote()
        }
    }

    private fun clearNote() {
        binding.noteEditText.setText("")
    }

    private fun setFlows() {
        collect(containerViewModel.container) { state ->
            when (state) {
                is ContainerUiState.RecognizeSpeech -> binding.noteEditText.setText(state.text)
                else -> return@collect
            }
        }
    }
}
