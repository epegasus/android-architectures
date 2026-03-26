package com.sohaib.modularization.data.favourites.di

import com.sohaib.modularization.data.favourites.repository.FavouritesRepositoryImpl
import org.koin.dsl.module
import com.sohaib.modularization.domain.favourites.repository.FavouritesRepository as DomainFavouritesRepository

/**
 * Koin module for Favourites data layer
 */
val favouritesDataModule = module {
    single<DomainFavouritesRepository> { FavouritesRepositoryImpl(get(), get()) }
}