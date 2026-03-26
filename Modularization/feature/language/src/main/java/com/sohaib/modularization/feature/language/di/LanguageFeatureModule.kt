package com.sohaib.modularization.feature.language.di

import com.sohaib.modularization.feature.language.viewModel.LanguageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for Language feature
 */
val languageFeatureModule = module {
    viewModel { LanguageViewModel(get(), get()) }
}
