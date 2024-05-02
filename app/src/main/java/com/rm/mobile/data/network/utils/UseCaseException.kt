/*
 * UseCaseException.kt
 * Created by Ulises Gonzalez on 02/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.rm.mobile.data.network.utils

import com.rm.mobile.utils.Constants

sealed class UseCaseException : kotlin.Exception() {
    class GenericException(
        val code: Int = 0,
        val errorMessage: String = Constants.EMPTY_STRING,
        vararg val params: Any?,
    ) : Exception(errorMessage) {
        var serverCode: Int = 0
        var serverMessage: String = Constants.EMPTY_STRING
    }
}
