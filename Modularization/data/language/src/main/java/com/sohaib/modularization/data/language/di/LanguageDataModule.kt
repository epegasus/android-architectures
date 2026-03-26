package com.sohaib.modularization.data.language.di

import com.sohaib.modularization.data.language.dataSource.LanguageDataSource
import com.sohaib.modularization.data.language.repository.LanguageRepositoryImpl
import com.sohaib.modularization.domain.language.repository.LanguageRepository
import org.koin.dsl.module

/**
 * Koin module for Language data layer
 */
val languageDataModule = module {
    single { LanguageDataSource() }
    single<LanguageRepository> { LanguageRepositoryImpl(get(), get(), get()) }
}