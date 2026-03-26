package com.sohaib.modularization.feature.favourites.intent

/**
 * Intents for Favourites screen
 */
sealed class FavouritesIntent {
    object LoadFavourites : FavouritesIntent()
    object SearchClicked : FavouritesIntent()
    object NavigationCleared : FavouritesIntent()
    object ErrorCleared : FavouritesIntent()
}