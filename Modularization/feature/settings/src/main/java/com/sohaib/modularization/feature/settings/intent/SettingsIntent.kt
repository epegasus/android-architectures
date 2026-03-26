package com.sohaib.modularization.feature.settings.intent

/**
 * Intents for Settings screen
 */
sealed class SettingsIntent {
    object LoadSettings : SettingsIntent()
    object RefreshSettings : SettingsIntent()
    data class UpdateTheme(val theme: String) : SettingsIntent()
    data class UpdateLanguage(val language: String) : SettingsIntent()
    data class UpdateNotifications(val enabled: Boolean) : SettingsIntent()
    data class UpdateAutoPlay(val enabled: Boolean) : SettingsIntent()
    data class UpdateImageQuality(val quality: String) : SettingsIntent()
    data class UpdateVideoQuality(val quality: String) : SettingsIntent()
    object ClearCache : SettingsIntent()
    object ResetSettings : SettingsIntent()
    object NavigationCleared : SettingsIntent()
    object ErrorCleared : SettingsIntent()
}