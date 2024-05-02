/*
 * TextWatcher.kt
 * Created by Ulises Gonzalez on 18/04/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Set text watcher
 */
fun EditText.onTetWatcher(callback: (String) -> Unit) {
    this.addTextChangedListener(
        object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int,
            ) {
                callback(s.toString())
            }
        },
    )
}
