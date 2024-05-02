/*
 * Animation.kt
 * Created by Ulises Gonzalez on 18/04/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

fun RecyclerView.clearItemAnimation() {
    this.itemAnimator = null
}

fun View.animationIn() {
    with(this) {
        alpha = Constants.ANIMATION_ALFA_HIDDEN
        show()
        animate()
            .alpha(Constants.ANIMATION_ALFA_VISIBLE)
            .setDuration(resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
            .setListener(null)
    }
}

fun View.animationOut() {
    this.animate()
        .alpha(Constants.ANIMATION_ALFA_HIDDEN)
        .setDuration(resources.getInteger(android.R.integer.config_longAnimTime).toLong())
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                this@animationOut.gone()
            }
        })
}

fun View.setImageViewAnimation(
    animationId: Int,
    onAnimationEnd: () -> Unit,
) {
    if (animationId != 0) {
        val animation = AnimationUtils.loadAnimation(context, animationId)
        animation.setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) = Unit

                override fun onAnimationEnd(animation: Animation?) {
                    onAnimationEnd()
                }

                override fun onAnimationStart(animation: Animation?) = Unit
            },
        )
        this.startAnimation(animation)
    } else onAnimationEnd()
}

fun Dialog.setAnimations(resource: Int) {
    if (resource != 0) {
        this.window?.setWindowAnimations(resource)
    }
}

fun KonfettiView.startAnimation() {
    this.apply {
        this.start(
            Party(
                speed = 0f,
                maxSpeed = 30f,
                damping = 0.9f,
                spread = 360,
                colors = listOf(0x78ba46, 0xb8d551, 0x14b3cb, 0x0d2324),
                position = Position.Relative(0.5, 0.4),
                emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100)
            )
        )
    }
}
