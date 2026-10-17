package com.deekseep.pro.config

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Preferences {
    private const val PREFS = "deekseep_prefs"
    private const val KEY_THEME = "theme_mode"

    const val THEME_SYSTEM = 0
    const val THEME_LIGHT = 1
    const val THEME_DARK = 2

    var themeMode by mutableStateOf(THEME_SYSTEM)
        private set

    fun init(ctx: Context) {
        val sp = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        themeMode = sp.getInt(KEY_THEME, THEME_SYSTEM)
    }

    fun setTheme(ctx: Context, mode: Int) {
        themeMode = mode
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit().putInt(KEY_THEME, mode).apply()
    }
}
