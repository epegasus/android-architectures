package com.sohaib.modularization.feature.settings.states

/**
 * Navigation destinations for Settings screen
 */
sealed class SettingsDestination {
    object ThemeChanged : SettingsDestination()
    object LanguageChanged : SettingsDestination()
    object CacheCleared : SettingsDestination()
    object SettingsReset : SettingsDestination()
}