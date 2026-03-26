package com.sohaib.modularization.domain.videos.usecase

import com.sohaib.modularization.domain.videos.entity.Video
import com.sohaib.modularization.domain.videos.repository.VideosRepository

/**
 * Use case for getting a video by ID
 */
class GetVideoByIdUseCase(private val videosRepository: VideosRepository) {

    suspend operator fun invoke(id: Long): Video? {
        return videosRepository.getVideoById(id)
    }
}