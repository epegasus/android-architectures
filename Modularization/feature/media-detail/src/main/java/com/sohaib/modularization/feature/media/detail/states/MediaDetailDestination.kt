package com.sohaib.modularization.feature.media.detail.states

/**
 * Navigation destinations for Media Detail screen
 */
sealed class MediaDetailDestination {
    object NavigateBack : MediaDetailDestination()
    object ShowShare : MediaDetailDestination()
    object ShowInfo : MediaDetailDestination()
}
