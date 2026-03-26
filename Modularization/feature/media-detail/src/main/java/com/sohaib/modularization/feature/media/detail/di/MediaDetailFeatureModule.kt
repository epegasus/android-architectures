package com.sohaib.modularization.feature.media.detail.di

import com.sohaib.modularization.feature.media.detail.viewmodel.MediaDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for Media Detail feature
 */
val mediaDetailFeatureModule = module {
    viewModel { MediaDetailViewModel(get(), get(), get()) }
}