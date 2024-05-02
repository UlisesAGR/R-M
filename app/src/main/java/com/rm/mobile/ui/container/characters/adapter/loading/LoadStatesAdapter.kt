package com.rm.mobile.ui.container.characters.adapter.loading

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class LoadStatesAdapter(
    private val retry: () -> Unit,
) : LoadStateAdapter<LoadStatesViewHolder>() {
    override fun onBindViewHolder(
        holder: LoadStatesViewHolder,
        loadState: LoadState,
    ) {
        holder.render(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): LoadStatesViewHolder =
        LoadStatesViewHolder.create(parent, retry)
}