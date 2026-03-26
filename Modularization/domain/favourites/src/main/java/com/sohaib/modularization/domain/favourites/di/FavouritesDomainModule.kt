package com.sohaib.modularization.domain.favourites.di

import com.sohaib.modularization.domain.favourites.usecase.GetFavouriteStatusesUseCase
import com.sohaib.modularization.domain.favourites.usecase.GetFavouritesUseCase
import com.sohaib.modularization.domain.favourites.usecase.IsFavouriteUseCase
import com.sohaib.modularization.domain.favourites.usecase.ToggleFavouriteUseCase
import org.koin.dsl.module

/**
 * Koin module for Favourites domain layer
 */
val favouritesDomainModule = module {
    factory { GetFavouritesUseCase(get()) }
    factory { ToggleFavouriteUseCase(get()) }
    factory { GetFavouriteStatusesUseCase(get()) }
    factory { IsFavouriteUseCase(get()) }
}