package com.sohaib.modularization.domain.entrance.di

import com.sohaib.modularization.domain.entrance.useCase.GetUserPreferencesEntranceUseCase
import org.koin.dsl.module

/**
 * Koin module for Language domain layer
 */
val entranceDomainModule = module {
    factory { GetUserPreferencesEntranceUseCase(get()) }
}