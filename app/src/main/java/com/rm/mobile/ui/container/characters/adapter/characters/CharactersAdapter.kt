/*
 * CharactersAdapter.kt
 * Created by Ulises Gonzalez on 21/04/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.ui.container.characters.adapter.characters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rm.mobile.databinding.ItemCharacterBinding
import com.rm.mobile.domain.model.Character

class CharactersAdapter(
    private val onItemSelected: (Character) -> Unit,
) : PagingDataAdapter<Character, CharactersViewHolder>(ORDER_COMPARATOR) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CharactersViewHolder =
        CharactersViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )

    override fun onBindViewHolder(
        holder: CharactersViewHolder,
        position: Int,
    ) {
        holder.render(getItem(position), onItemSelected)
        //setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        val animation = ObjectAnimator.ofFloat(viewToAnimate, "translationY", 200f, 0f)
        animation.duration = 500
        val animatorSet = AnimatorSet()
        animatorSet.play(animation)
        animatorSet.startDelay = (100 * position).toLong()
        animatorSet.start()
    }

    companion object {
        private val ORDER_COMPARATOR =
            object : DiffUtil.ItemCallback<Character>() {
                override fun areItemsTheSame(
                    oldItem: Character,
                    newItem: Character,
                ) = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: Character,
                    newItem: Character,
                ) = oldItem == newItem
            }
    }
}
