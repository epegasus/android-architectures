package com.sohaib.modularization.domain.videos.repository

import com.sohaib.modularization.domain.videos.entity.Video
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for videos data operations
 */
interface VideosRepository {

    /**
     * Get all videos from MediaStore
     */
    suspend fun getVideos(): Flow<List<Video>>

    /**
     * Get videos with pagination
     */
    suspend fun getVideosPaginated(pageSize: Int, offset: Int): Flow<List<Video>>

    /**
     * Get video by ID
     */
    suspend fun getVideoById(id: Long): Video?

    /**
     * Search videos by name
     */
    suspend fun searchVideos(query: String): Flow<List<Video>>

    /**
     * Get videos by date range
     */
    suspend fun getVideosByDateRange(startDate: Long, endDate: Long): Flow<List<Video>>

    /**
     * Get videos by duration range
     */
    suspend fun getVideosByDurationRange(minDuration: Long, maxDuration: Long): Flow<List<Video>>

    /**
     * Get videos by size range
     */
    suspend fun getVideosBySizeRange(minSize: Long, maxSize: Long): Flow<List<Video>>
}
