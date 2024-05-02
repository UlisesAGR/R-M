/*
 * EmptyState.kt
 * Created by Ulises Gonzalez on 21/04/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.greenbox.emptystates

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.rm.mobile.greenbox.R
import com.rm.mobile.greenbox.databinding.EmptyStateBinding
import com.rm.mobile.utils.AppLog.Companion.log
import com.rm.mobile.utils.animationIn
import com.rm.mobile.utils.show

class EmptyState @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        EmptyStateBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.EmptyState).apply {
            try {
                showEmptyState()
                setImage(getResourceId(R.styleable.EmptyState_imageEmptyStateList, 0))
                setDescription(getString(R.styleable.EmptyState_descriptionEmptyStateList))
                setTitle(getString(R.styleable.EmptyState_titleEmptyStateList))
            } catch (exception: Exception) {
                log.exception(
                    tag = this,
                    exception,
                )
            } finally {
                recycle()
            }
        }
    }

    private fun setImage(image: Int) {
        with(binding.emptyStateImageView) {
            if (image != 0) {
                setImageResource(image)
                show()
            }
        }
    }

    private fun setTitle(title: String?) {
        with(binding.titleTextView) {
            if (!title.isNullOrEmpty()) {
                text = title
                show()
            }
        }
    }

    private fun setDescription(description: String?) {
        with(binding.descriptionTextView) {
            if (!description.isNullOrEmpty()) {
                text = description
                show()
            }
        }
    }

    private fun showEmptyState() {
        clearAnimation()
        binding.emptyStateRoot.animationIn()
    }
}
