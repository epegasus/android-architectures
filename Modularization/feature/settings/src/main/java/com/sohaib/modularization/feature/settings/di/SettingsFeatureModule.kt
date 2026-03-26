package com.sohaib.modularization.feature.settings.di

import com.sohaib.modularization.feature.settings.viewmodel.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for Settings feature
 */
val settingsFeatureModule = module {
    viewModel { SettingsViewModel(get(), get(), get()) }
}