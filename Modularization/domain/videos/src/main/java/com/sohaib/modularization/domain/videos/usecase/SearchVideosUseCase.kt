package com.sohaib.modularization.domain.videos.usecase

import com.sohaib.modularization.domain.videos.entity.Video
import com.sohaib.modularization.domain.videos.repository.VideosRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for searching videos
 */
class SearchVideosUseCase(private val videosRepository: VideosRepository) {

    suspend operator fun invoke(query: String): Flow<List<Video>> {
        return videosRepository.searchVideos(query)
    }
}