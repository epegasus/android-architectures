package com.sohaib.modularization.domain.favourites.usecase

import com.sohaib.modularization.domain.favourites.repository.FavouritesRepository

/**
 * Use case for checking if an item is favourite
 */
class IsFavouriteUseCase(private val favouritesRepository: FavouritesRepository) {

    suspend operator fun invoke(mediaId: Long): Boolean {
        return favouritesRepository.isFavourite(mediaId)
    }
}