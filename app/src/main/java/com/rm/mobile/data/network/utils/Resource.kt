/*
 * Resource.kt
 * Created by Ulises Gonzalez on 02/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.rm.mobile.data.network.utils

sealed class Resource<out T>(
    val status: Int,
    val type: String? = null,
    val data: T? = null,
    val stringCode: String? = null,
    val details: String? = null,
    val moreInfo: String? = null,
    val cause: Throwable? = null,
) {
    class Success<T>(
        data: T,
        details: String? = null,
        stringCode: String? = null,
    ) : Resource<T>(
        status = SUCCESS,
        data = data,
        details = details,
        stringCode = stringCode,
    )

    class Failure<T>(
        status: Int,
        stringCode: String?,
        details: String?,
        type: String? = null,
        moreInfo: String? = null,
        cause: Throwable? = null,
    ) : Resource<T>(
        status = status,
        type = type,
        stringCode = stringCode,
        details = details,
        moreInfo = moreInfo,
        cause = cause
    )

    fun isSuccessful(): Boolean = status in 200..300

    companion object {
        const val SUCCESS = 200
    }
}
