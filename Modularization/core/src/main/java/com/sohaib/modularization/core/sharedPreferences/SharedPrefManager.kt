package com.sohaib.modularization.core.sharedPreferences

import android.content.Context
import androidx.core.content.edit

private const val KEY_CURRENT_LANGUAGE = "current_language"
private const val KEY_COMPLETE_ONBOARDING = "complete_onboarding"
private const val DEFAULT_LANGUAGE = "en"

class SharedPrefManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    var currentLanguage: String
        get() = sharedPreferences.getString(KEY_CURRENT_LANGUAGE, DEFAULT_LANGUAGE) ?: DEFAULT_LANGUAGE
        set(value) = sharedPreferences.edit { putString(KEY_CURRENT_LANGUAGE, value) }

    var markOnBoardingComplete: Boolean
        get() = sharedPreferences.getBoolean(KEY_COMPLETE_ONBOARDING, false)
        set(value) = sharedPreferences.edit { putBoolean(KEY_COMPLETE_ONBOARDING, value) }

}