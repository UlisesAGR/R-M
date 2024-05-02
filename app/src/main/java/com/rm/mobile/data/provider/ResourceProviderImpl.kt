/*
 * ResourceProviderImpl.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2024. All rights reserved.
 */
package com.rm.mobile.data.provider

import android.content.Context
import com.rm.mobile.R
import com.rm.mobile.domain.provider.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext appContext: Context,
): ResourceProvider {
    private val resource = appContext.resources

    override fun getErrorGettingCharacterLabel(): String =
        resource.getString(R.string.app_error_getting_character)
}
