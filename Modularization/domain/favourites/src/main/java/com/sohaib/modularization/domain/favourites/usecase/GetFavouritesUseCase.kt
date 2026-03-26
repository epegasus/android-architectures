package com.sohaib.modularization.domain.favourites.usecase

import com.sohaib.modularization.domain.favourites.entity.Favourite
import com.sohaib.modularization.domain.favourites.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting all favourites
 */
class GetFavouritesUseCase(private val favouritesRepository: FavouritesRepository) {

    suspend operator fun invoke(): Flow<List<Favourite>> {
        return favouritesRepository.getFavourites()
    }
}