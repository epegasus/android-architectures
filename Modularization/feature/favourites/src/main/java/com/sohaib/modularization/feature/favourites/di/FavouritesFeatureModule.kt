package com.sohaib.modularization.feature.favourites.di

import com.sohaib.modularization.feature.favourites.viewmodel.FavouritesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for Favourites feature
 */
val favouritesFeatureModule = module {
    viewModel { FavouritesViewModel(get(), get()) }
}