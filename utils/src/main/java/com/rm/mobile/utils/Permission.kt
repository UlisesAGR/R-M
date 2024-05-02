/*
 * Permission.kt
 * Created by Eduardo on 18/04/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.rm.mobile.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

/**
 *This function will validate if you have the permissions
 */
fun Context.checkPermission(permission: String): Boolean =
    (ContextCompat.checkSelfPermission(
        this,
        permission,
    ) == PackageManager.PERMISSION_GRANTED)
