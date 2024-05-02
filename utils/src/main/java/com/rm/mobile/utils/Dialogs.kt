/*
 * Dialogs.kt
 * Created by Ulises Gonzalez on 18/04/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.utils

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * This extension show material dialog
 */
fun Context.materialDialog(
    title: String,
    message: String,
    textButton: String,
) = MaterialAlertDialogBuilder(this)
    .setTitle(title)
    .setMessage(message)
    .setPositiveButton(textButton) { dialog, _ ->
        dialog.dismiss()
    }
    .setCancelable(false)
    .show()

fun Context.materialDialog(
    title: String,
    message: String,
    textButton: String,
    onClick: () -> Unit,
) = MaterialAlertDialogBuilder(this)
    .setTitle(title)
    .setMessage(message)
    .setPositiveButton(textButton) { dialog, _ ->
        onClick()
        dialog.dismiss()
    }
    .setCancelable(false)
    .show()

fun Context.materialDialog(
    title: String,
    message: String,
    textPositiveButton: String,
    textNegativeButton: String,
    onClick: () -> Unit,
) = MaterialAlertDialogBuilder(this)
    .setTitle(title)
    .setMessage(message)
    .setPositiveButton(textPositiveButton) { dialog, _ ->
        onClick()
        dialog.dismiss()
    }
    .setNegativeButton(textNegativeButton) { dialog, _ ->
        dialog.dismiss()
    }
    .setCancelable(false)
    .show()
