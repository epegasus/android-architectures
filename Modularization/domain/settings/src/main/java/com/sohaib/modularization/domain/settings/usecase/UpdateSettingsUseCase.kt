package com.sohaib.modularization.domain.settings.usecase

import com.sohaib.modularization.domain.settings.entity.Settings
import com.sohaib.modularization.domain.settings.repository.SettingsRepository

/**
 * Use case for updating app settings
 */
class UpdateSettingsUseCase(private val settingsRepository: SettingsRepository) {

    suspend operator fun invoke(settings: Settings) {
        settingsRepository.updateSettings(settings)
    }
}