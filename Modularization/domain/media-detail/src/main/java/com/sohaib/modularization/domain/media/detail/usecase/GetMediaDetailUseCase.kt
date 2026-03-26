package com.sohaib.modularization.domain.media.detail.usecase

import com.sohaib.modularization.domain.media.detail.entity.MediaDetail
import com.sohaib.modularization.domain.media.detail.repository.MediaDetailRepository

/**
 * Use case for getting media detail
 */
class GetMediaDetailUseCase(private val mediaDetailRepository: MediaDetailRepository) {

    suspend operator fun invoke(mediaId: Long, mediaType: String): MediaDetail? {
        return mediaDetailRepository.getMediaDetail(mediaId, mediaType)
    }
}