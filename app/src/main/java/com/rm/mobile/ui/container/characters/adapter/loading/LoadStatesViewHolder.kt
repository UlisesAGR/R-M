package com.rm.mobile.ui.container.characters.adapter.loading

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.rm.mobile.R
import com.rm.mobile.databinding.ItemLoadingBinding
import com.rm.mobile.utils.show

class LoadStatesViewHolder(
    private val binding: ItemLoadingBinding,
    private val retry: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun render(
        loadState: LoadState,
    ) = with(binding) {
        progressBar.isVisible = loadState is LoadState.Loading
        if (loadState is LoadState.Error) {
            errorTextView.apply {
                text = context.getString(R.string.app_place_try_again_later)
                show()
            }
        }
        refreshButton.apply {
            isVisible = loadState is LoadState.Error
            refreshButton.setOnClickListener { retry() }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            retry: () -> Unit,
        ): LoadStatesViewHolder = LoadStatesViewHolder(
            ItemLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            retry,
        )
    }
}
