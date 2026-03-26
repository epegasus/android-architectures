package com.sohaib.modularization.data.settings.mapper

import com.sohaib.modularization.data.settings.model.SettingsEntity
import com.sohaib.modularization.domain.settings.entity.Settings

/**
 * Extension function to map SettingsEntity to Settings domain entity
 */
fun SettingsEntity.mapToDomain(): Settings {
    return Settings(
        theme = this.theme.name,
        language = this.language,
        notifications = this.notifications,
        autoPlay = this.autoPlay,
        imageQuality = this.imageQuality.name,
        videoQuality = this.videoQuality.name,
        cacheSize = this.cacheSize,
        lastBackup = this.lastBackup
    )
}