package com.sohaib.modularization.domain.settings.repository

import com.sohaib.modularization.domain.settings.entity.Settings
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for settings data operations
 */
interface SettingsRepository {

    /**
     * Get current settings
     */
    suspend fun getSettings(): Flow<Settings>

    /**
     * Update settings
     */
    suspend fun updateSettings(settings: Settings)

    /**
     * Update theme
     */
    suspend fun updateTheme(theme: String)

    /**
     * Update language
     */
    suspend fun updateLanguage(language: String)

    /**
     * Update notifications setting
     */
    suspend fun updateNotifications(enabled: Boolean)

    /**
     * Update auto play setting
     */
    suspend fun updateAutoPlay(enabled: Boolean)

    /**
     * Update image quality
     */
    suspend fun updateImageQuality(quality: String)

    /**
     * Update video quality
     */
    suspend fun updateVideoQuality(quality: String)

    /**
     * Clear cache
     */
    suspend fun clearCache()

    /**
     * Reset to default settings
     */
    suspend fun resetToDefaults()
}
