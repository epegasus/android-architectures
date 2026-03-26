package com.sohaib.modularization.data.settings.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.sohaib.modularization.data.settings.mapper.mapToDomain
import com.sohaib.modularization.data.settings.model.AppTheme
import com.sohaib.modularization.data.settings.model.ImageQuality
import com.sohaib.modularization.data.settings.model.SettingsEntity
import com.sohaib.modularization.data.settings.model.VideoQuality
import com.sohaib.modularization.domain.settings.entity.Settings
import com.sohaib.modularization.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Implementation of SettingsRepository using SharedPreferences
 */
class SettingsRepositoryImpl(
    context: Context,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : SettingsRepository {

    private val prefs: SharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_THEME = "theme"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_NOTIFICATIONS = "notifications"
        private const val KEY_AUTO_PLAY = "auto_play"
        private const val KEY_IMAGE_QUALITY = "image_quality"
        private const val KEY_VIDEO_QUALITY = "video_quality"
        private const val KEY_CACHE_SIZE = "cache_size"
        private const val KEY_LAST_BACKUP = "last_backup"
    }

    override suspend fun getSettings(): Flow<Settings> = flow {
        val settingsEntity = SettingsEntity(
            theme = AppTheme.valueOf(prefs.getString(KEY_THEME, AppTheme.SYSTEM.name) ?: AppTheme.SYSTEM.name),
            language = prefs.getString(KEY_LANGUAGE, "en") ?: "en",
            notifications = prefs.getBoolean(KEY_NOTIFICATIONS, true),
            autoPlay = prefs.getBoolean(KEY_AUTO_PLAY, false),
            imageQuality = ImageQuality.valueOf(prefs.getString(KEY_IMAGE_QUALITY, ImageQuality.HIGH.name) ?: ImageQuality.HIGH.name),
            videoQuality = VideoQuality.valueOf(prefs.getString(KEY_VIDEO_QUALITY, VideoQuality.HIGH.name) ?: VideoQuality.HIGH.name),
            cacheSize = prefs.getLong(KEY_CACHE_SIZE, 0L),
            lastBackup = prefs.getLong(KEY_LAST_BACKUP, 0L)
        )
        emit(settingsEntity.mapToDomain())
    }.flowOn(defaultDispatcher)

    override suspend fun updateSettings(settings: Settings) {
        prefs.edit().apply {
            putString(KEY_THEME, settings.theme)
            putString(KEY_LANGUAGE, settings.language)
            putBoolean(KEY_NOTIFICATIONS, settings.notifications)
            putBoolean(KEY_AUTO_PLAY, settings.autoPlay)
            putString(KEY_IMAGE_QUALITY, settings.imageQuality)
            putString(KEY_VIDEO_QUALITY, settings.videoQuality)
            putLong(KEY_CACHE_SIZE, settings.cacheSize)
            putLong(KEY_LAST_BACKUP, settings.lastBackup)
            apply()
        }
    }

    override suspend fun updateTheme(theme: String) {
        prefs.edit { putString(KEY_THEME, theme) }
    }

    override suspend fun updateLanguage(language: String) {
        prefs.edit { putString(KEY_LANGUAGE, language) }
    }

    override suspend fun updateNotifications(enabled: Boolean) {
        prefs.edit { putBoolean(KEY_NOTIFICATIONS, enabled) }
    }

    override suspend fun updateAutoPlay(enabled: Boolean) {
        prefs.edit { putBoolean(KEY_AUTO_PLAY, enabled) }
    }

    override suspend fun updateImageQuality(quality: String) {
        prefs.edit { putString(KEY_IMAGE_QUALITY, quality) }
    }

    override suspend fun updateVideoQuality(quality: String) {
        prefs.edit { putString(KEY_VIDEO_QUALITY, quality) }
    }

    override suspend fun clearCache() {
        prefs.edit { putLong(KEY_CACHE_SIZE, 0L) }
    }

    override suspend fun resetToDefaults() {
        prefs.edit { clear() }
    }
}