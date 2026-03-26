package com.sohaib.modularization.domain.favourites.usecase

import com.sohaib.modularization.domain.favourites.entity.Favourite
import com.sohaib.modularization.domain.favourites.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for searching favourites
 */
class SearchFavouritesUseCase(private val favouritesRepository: FavouritesRepository) {

    suspend operator fun invoke(query: String): Flow<List<Favourite>> {
        return favouritesRepository.searchFavourites(query)
    }
}