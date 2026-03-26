package com.sohaib.modularization.domain.settings.di

import com.sohaib.modularization.domain.settings.usecase.GetSettingsUseCase
import com.sohaib.modularization.domain.settings.usecase.ResetSettingsUseCase
import com.sohaib.modularization.domain.settings.usecase.UpdateSettingsUseCase
import org.koin.dsl.module

/**
 * Koin module for Settings domain layer
 */
val settingsDomainModule = module {
    factory { GetSettingsUseCase(get()) }
    factory { UpdateSettingsUseCase(get()) }
    factory { ResetSettingsUseCase(get()) }
}