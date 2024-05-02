/*
 * ViewVisibility.kt
 * Created by Ulises Gonzalez on 18/04/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.utils

import android.view.View

/**
 * Valid if not visible make visible the view
 */
fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Valid if not gone make gone the view
 */
fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.progressVisibility(isVisible: Boolean) =
    if (isVisible) visibility = View.VISIBLE else visibility = View.GONE

fun View.layoutVisibility(isVisible: Boolean) =
    if (isVisible) visibility = View.GONE else visibility = View.VISIBLE
