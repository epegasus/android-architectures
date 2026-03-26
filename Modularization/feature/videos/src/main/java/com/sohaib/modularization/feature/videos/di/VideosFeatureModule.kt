package com.sohaib.modularization.feature.videos.di

import com.sohaib.modularization.feature.videos.viewmodel.VideosViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for Videos feature
 */
val videosFeatureModule = module {
    viewModel { VideosViewModel(get(), get(), get()) }
}