package com.romvaz.core.ui.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings

object GlobalUtils {
    fun goToSettings(context: Context) {
        val intent = Intent()
        intent.action = Settings.ACTION_SETTINGS
        context.startActivity(intent)
    }
}
