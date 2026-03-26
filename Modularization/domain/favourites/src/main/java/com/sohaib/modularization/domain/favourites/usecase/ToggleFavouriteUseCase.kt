package com.sohaib.modularization.domain.favourites.usecase

import com.sohaib.modularization.domain.favourites.repository.FavouritesRepository

/**
 * Use case for toggling favourite status
 */
class ToggleFavouriteUseCase(private val favouritesRepository: FavouritesRepository) {

    suspend operator fun invoke(
        mediaId: Long,
        mediaType: String,
        name: String,
        uri: String,
        size: Long,
        dateAdded: Long
    ): Boolean {
        return favouritesRepository.toggleFavourite(
            mediaId = mediaId,
            mediaType = mediaType,
            name = name,
            uri = uri,
            size = size,
            dateAdded = dateAdded
        )
    }
}