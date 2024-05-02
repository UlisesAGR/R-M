package com.rm.mobile.utils

import android.Manifest

enum class PermissionType(val value: String) {
    CAMERA(Manifest.permission.CAMERA),
    PHONE(Manifest.permission.CALL_PHONE),
    STORAGE(Manifest.permission.READ_EXTERNAL_STORAGE),
    LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION),
}
