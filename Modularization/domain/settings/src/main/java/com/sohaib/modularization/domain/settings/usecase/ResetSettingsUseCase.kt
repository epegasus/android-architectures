package com.sohaib.modularization.domain.settings.usecase

import com.sohaib.modularization.domain.settings.repository.SettingsRepository

/**
 * Use case for resetting settings to default
 */
class ResetSettingsUseCase(private val settingsRepository: SettingsRepository) {

    suspend operator fun invoke() {
        settingsRepository.resetToDefaults()
    }
}