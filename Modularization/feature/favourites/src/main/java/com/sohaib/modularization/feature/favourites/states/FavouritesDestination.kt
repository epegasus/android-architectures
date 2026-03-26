package com.sohaib.modularization.feature.favourites.states

import com.sohaib.modularization.domain.favourites.entity.Favourite

/**
 * Navigation destinations for Favourites screen
 */
sealed class FavouritesDestination {
    data class FavouriteDetail(val favourite: Favourite) : FavouritesDestination()
    object Search : FavouritesDestination()
}