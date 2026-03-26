package com.sohaib.modularization.data.settings.di

import com.sohaib.modularization.data.settings.repository.SettingsRepositoryImpl
import com.sohaib.modularization.domain.settings.repository.SettingsRepository
import org.koin.dsl.module

/**
 * Koin module for Settings data layer
 */
val settingsDataModule = module {
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}