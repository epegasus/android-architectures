package com.sohaib.modularization.domain.images.repository

import com.sohaib.modularization.domain.images.entity.Image
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for images data operations
 */
interface ImagesRepository {

    /**
     * Get all images from MediaStore
     */
    suspend fun getImages(): Flow<List<Image>>

    /**
     * Get images with pagination
     */
    suspend fun getImagesPaginated(pageSize: Int, offset: Int): Flow<List<Image>>

    /**
     * Get image by ID
     */
    suspend fun getImageById(id: Long): Image?

    /**
     * Search images by name
     */
    suspend fun searchImages(query: String): Flow<List<Image>>

    /**
     * Get images by date range
     */
    suspend fun getImagesByDateRange(startDate: Long, endDate: Long): Flow<List<Image>>

    /**
     * Get images by size range
     */
    suspend fun getImagesBySizeRange(minSize: Long, maxSize: Long): Flow<List<Image>>
}