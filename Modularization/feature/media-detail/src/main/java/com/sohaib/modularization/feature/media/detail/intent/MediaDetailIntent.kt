package com.sohaib.modularization.feature.media.detail.intent

/**
 * Intents for Media Detail screen
 */
sealed class MediaDetailIntent {
    data class LoadMedia(val mediaId: Long, val mediaType: String) : MediaDetailIntent()
    object RefreshMedia : MediaDetailIntent()
    object FavouriteClicked : MediaDetailIntent()
    object ShareClicked : MediaDetailIntent()
    object InfoClicked : MediaDetailIntent()
    object NavigationCleared : MediaDetailIntent()
    object ErrorCleared : MediaDetailIntent()
}
