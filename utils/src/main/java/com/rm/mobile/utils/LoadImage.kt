/*
 * ImageView.kt
 * Created by Ulises Gonzalez on 14/04/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.loadImage(image: String?) {
    Glide.with(this)
        .load(image)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade(300))
        .placeholder(android.R.drawable.progress_horizontal)
        .error(android.R.drawable.progress_horizontal)
        .into(this)
}

fun ImageView.loadImageCircle(image: String?) {
    Glide.with(this)
        .load(image)
        .circleCrop()
        .transition(DrawableTransitionOptions.withCrossFade(300))
        .placeholder(android.R.drawable.progress_horizontal)
        .error(android.R.drawable.progress_horizontal)
        .into(this)
}
