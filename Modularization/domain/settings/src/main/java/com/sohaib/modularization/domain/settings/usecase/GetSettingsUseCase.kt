package com.sohaib.modularization.domain.settings.usecase

import com.sohaib.modularization.domain.settings.entity.Settings
import com.sohaib.modularization.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting app settings
 */
class GetSettingsUseCase(private val settingsRepository: SettingsRepository) {

    suspend operator fun invoke(): Flow<Settings> {
        return settingsRepository.getSettings()
    }
}