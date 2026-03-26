package com.sohaib.modularization.feature.images.intent

/**
 * Intents for Images screen
 */
sealed class ImagesIntent {
    object LoadImages : ImagesIntent()
    object RefreshImages : ImagesIntent()
    object SearchClicked : ImagesIntent()
    object NavigationCleared : ImagesIntent()
    object ErrorCleared : ImagesIntent()
}