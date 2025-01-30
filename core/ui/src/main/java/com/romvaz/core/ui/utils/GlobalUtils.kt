package com.romvaz.core.ui.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings

// This Class must contain global functions for Modules uses
object GlobalUtils {
    fun goToSettings(context: Context) {
        val intent = Intent()
        intent.action = Settings.ACTION_SETTINGS
        context.startActivity(intent)
    }

    fun String.validateMail() = Regex(REGEX_EMAIL).matches(this)
    fun String.validatePassword() = Regex(REGEX_PASSWORD).matches(this)
}
