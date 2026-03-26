package com.sohaib.modularization.feature.favourites.states

import com.sohaib.modularization.domain.favourites.entity.Favourite

/**
 * State for Favourites screen
 */
data class FavouritesState(
    val isLoading: Boolean = false,
    val favourites: List<Favourite> = emptyList(),
    val errorMessageRes: Int? = null,
    val navigateTo: FavouritesDestination? = null
)