package com.sohaib.modularization.domain.videos.usecase

import com.sohaib.modularization.domain.videos.entity.Video
import com.sohaib.modularization.domain.videos.repository.VideosRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting all videos
 */
class GetVideosUseCase(private val videosRepository: VideosRepository) {

    suspend operator fun invoke(): Flow<List<Video>> {
        return videosRepository.getVideos()
    }
}