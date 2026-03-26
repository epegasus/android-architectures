package com.sohaib.modularization.data.settings.model

/**
 * Data class representing app settings
 */
data class SettingsEntity(
    val theme: AppTheme = AppTheme.SYSTEM,
    val language: String = "en",
    val notifications: Boolean = true,
    val autoPlay: Boolean = false,
    val imageQuality: ImageQuality = ImageQuality.HIGH,
    val videoQuality: VideoQuality = VideoQuality.HIGH,
    val cacheSize: Long = 0L,
    val lastBackup: Long = 0L
)

/**
 * App theme options
 */
enum class AppTheme {
    LIGHT,
    DARK,
    SYSTEM
}

/**
 * Image quality options
 */
enum class ImageQuality {
    LOW,
    MEDIUM,
    HIGH,
    ORIGINAL
}

/**
 * Video quality options
 */
enum class VideoQuality {
    LOW,
    MEDIUM,
    HIGH,
    ORIGINAL
}