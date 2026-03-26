package com.sohaib.modularization.feature.entrance.di

import com.sohaib.modularization.feature.entrance.viewModel.EntranceViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for Entrance feature
 */
val entranceFeatureModule = module {
    viewModel { EntranceViewModel(get()) }
}