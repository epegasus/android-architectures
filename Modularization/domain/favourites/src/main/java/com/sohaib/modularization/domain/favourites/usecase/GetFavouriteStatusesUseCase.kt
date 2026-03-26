package com.sohaib.modularization.domain.favourites.usecase

import com.sohaib.modularization.domain.favourites.repository.FavouritesRepository

/**
 * Use case for getting favourite statuses for multiple media items in batch
 */
class GetFavouriteStatusesUseCase(private val favouritesRepository: FavouritesRepository) {

    suspend operator fun invoke(mediaIds: List<Long>): Map<Long, Boolean> {
        return favouritesRepository.getFavouriteStatuses(mediaIds)
    }
}