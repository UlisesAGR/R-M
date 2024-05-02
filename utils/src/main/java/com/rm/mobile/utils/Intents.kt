package com.rm.mobile.utils

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.speech.RecognizerIntent
import androidx.fragment.app.FragmentActivity
import java.util.Locale


/**
 * This method change a new activity
 */
fun FragmentActivity.nextActivity(activity: FragmentActivity) {
    Intent(this, activity::class.java).apply {
        startActivity(this)
    }
}

fun FragmentActivity.launchSettings(packageName: String) {
    startActivity(
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName"),
        )
    )
}

fun FragmentActivity.launchCallPhone(phone: String) {
    startActivity(
        Intent(
            Intent.ACTION_CALL,
            Uri.parse("tel:$phone"),
        )
    )
}

fun FragmentActivity.recognizeSpeechIntent(onError: () -> Unit) =
    Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM,
        )
        putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            Locale.getDefault(),
        )
        if (resolveActivity(packageManager) == null) {
            onError()
        }
    }
