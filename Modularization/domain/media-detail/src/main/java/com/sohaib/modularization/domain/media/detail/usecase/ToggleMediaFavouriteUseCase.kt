package com.sohaib.modularization.domain.media.detail.usecase

import com.sohaib.modularization.domain.media.detail.repository.MediaDetailRepository

/**
 * Use case for toggling media favourite status
 */
class ToggleMediaFavouriteUseCase(private val mediaDetailRepository: MediaDetailRepository) {

    suspend operator fun invoke(
        mediaId: Long,
        mediaType: String,
        name: String,
        uri: String,
        size: Long,
        dateAdded: Long
    ): Boolean {
        return mediaDetailRepository.toggleFavourite(mediaId, mediaType, name, uri, size, dateAdded)
    }
}