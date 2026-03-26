package com.sohaib.modularization.domain.media.detail.usecase

import com.sohaib.modularization.domain.media.detail.repository.MediaDetailRepository

/**
 * Use case for checking if media is favourite
 */
class IsMediaFavouriteUseCase(private val mediaDetailRepository: MediaDetailRepository) {

    suspend operator fun invoke(mediaId: Long): Boolean {
        return mediaDetailRepository.isFavourite(mediaId)
    }
}