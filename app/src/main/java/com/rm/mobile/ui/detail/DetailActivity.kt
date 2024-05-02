package com.rm.mobile.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.rm.mobile.R
import com.rm.mobile.databinding.ActivityDetailBinding
import com.rm.mobile.domain.model.Character
import com.rm.mobile.ui.detail.viewModel.DetailUiState
import com.rm.mobile.ui.detail.viewModel.DetailViewModel
import com.rm.mobile.utils.StatusCharacter
import com.rm.mobile.utils.collect
import com.rm.mobile.utils.layoutVisibility
import com.rm.mobile.utils.loadImage
import com.rm.mobile.utils.materialDialog
import com.rm.mobile.utils.progressVisibility
import com.rm.mobile.utils.show
import com.rm.mobile.utils.toStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val detailViewModel: DetailViewModel by viewModels()

    private val args: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInitUi()
    }

    private fun setInitUi() {
        detailViewModel.getCharacter(args.id)
        setListeners()
        setFlows()
    }

    private fun setListeners() =
        with(binding) {
            detailToolbar.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            backButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }

    private fun setFlows() {
        collect(detailViewModel.detail) { state ->
            when (state) {
                is DetailUiState.Loading -> validateLoading(state.isLoading)
                is DetailUiState.ShowDetail -> setData(state.character)
                is DetailUiState.Error -> openDialog(state.message)
            }
        }
    }

    private fun validateLoading(isLoading: Boolean) =
        with(binding) {
            shimmerFrameLayout.progressVisibility(isLoading)
            materialCardView.layoutVisibility(isLoading)
        }

    private fun setData(character: Character) {
        character.apply {
            setName(name)
            setSpecie(specie)
            loadImage(image)
            setStatus(status)
        }
    }

    private fun setName(name: String?) {
        with(binding.nameTextView) {
            if (!name.isNullOrEmpty()) {
                text = name
                show()
            }
        }
    }

    private fun setSpecie(specie: String?) {
        with(binding.specieTextView) {
            if (!specie.isNullOrEmpty()) {
                text = specie
                show()
            }
        }
    }

    private fun loadImage(image: String?) {
        with(binding.characterImageView) {
            if (!image.isNullOrEmpty()) {
                loadImage(image)
                show()
            }
        }
    }

    private fun setStatus(status: String?) = with(binding) {
        if (!status.isNullOrEmpty()) {
            statusTextView.text = status
            setStatusColor(status.toStatus())
            statusLayout.show()
        }
    }

    private fun setStatusColor(status: StatusCharacter) {
        binding.statusImageView.setColorFilter(
            getColor(
                when (status) {
                    StatusCharacter.ALIVE -> com.rm.mobile.greenbox.R.color.widgets_color
                    StatusCharacter.DEAD -> com.rm.mobile.greenbox.R.color.widgets_red
                    StatusCharacter.UNKNOWN -> com.rm.mobile.greenbox.R.color.widgets_white
                }
            )
        )
    }

    private fun openDialog(message: String) {
        materialDialog(
            title = getString(R.string.app_message),
            message,
            textButton = getString(R.string.app_accept),
        )
    }
}
