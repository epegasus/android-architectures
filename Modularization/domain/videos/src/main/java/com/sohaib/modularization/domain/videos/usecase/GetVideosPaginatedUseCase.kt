package com.sohaib.modularization.domain.videos.usecase

import com.sohaib.modularization.domain.videos.entity.Video
import com.sohaib.modularization.domain.videos.repository.VideosRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting videos with pagination
 */
class GetVideosPaginatedUseCase(private val videosRepository: VideosRepository) {

    suspend operator fun invoke(pageSize: Int = 50, offset: Int = 0): Flow<List<Video>> {
        return videosRepository.getVideosPaginated(pageSize, offset)
    }
}