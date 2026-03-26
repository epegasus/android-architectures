package com.sohaib.modularization.domain.language.di

import com.sohaib.modularization.domain.language.usecase.GetCurrentLanguageUseCase
import com.sohaib.modularization.domain.language.usecase.GetLanguagesUseCase
import com.sohaib.modularization.domain.language.usecase.SetLanguageUseCase
import org.koin.dsl.module

/**
 * Koin module for Language domain layer
 */
val languageDomainModule = module {
    factory { GetLanguagesUseCase(get()) }
    factory { GetCurrentLanguageUseCase(get()) }
    factory { SetLanguageUseCase(get()) }
}