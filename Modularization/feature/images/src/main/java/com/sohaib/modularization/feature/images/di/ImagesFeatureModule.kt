package com.sohaib.modularization.feature.images.di

import com.sohaib.modularization.feature.images.viewmodel.ImagesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for Images feature
 */
val imagesFeatureModule = module {
    viewModel { ImagesViewModel(get(), get(), get()) }
}