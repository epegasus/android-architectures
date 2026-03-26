package com.sohaib.modularization.feature.videos.intent

/**
 * Intents for Videos screen
 */
sealed class VideosIntent {
    object LoadVideos : VideosIntent()
    object RefreshVideos : VideosIntent()
    object SearchClicked : VideosIntent()
    object NavigationCleared : VideosIntent()
    object ErrorCleared : VideosIntent()
}