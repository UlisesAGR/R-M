/*
 * AppLog.kt
 * Created by Ulises Gonzalez on 18/04/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.utils

class AppLog {
    fun exception(
        tag: Any,
        exception: Throwable,
    ) {
        if (BuildConfig.DEBUG) {
            exception(tag, exception)
        }
    }

    companion object {
        @JvmField
        val log = AppLog()
    }
}
