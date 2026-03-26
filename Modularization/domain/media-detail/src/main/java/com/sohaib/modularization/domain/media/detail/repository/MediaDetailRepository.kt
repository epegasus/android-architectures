package com.sohaib.modularization.domain.media.detail.repository

import com.sohaib.modularization.domain.media.detail.entity.MediaDetail

/**
 * Repository interface for media detail operations
 */
interface MediaDetailRepository {

    /**
     * Get media detail by ID and type
     */
    suspend fun getMediaDetail(mediaId: Long, mediaType: String): MediaDetail?

    /**
     * Check if media is favourite
     */
    suspend fun isFavourite(mediaId: Long): Boolean

    /**
     * Toggle favourite status
     */
    suspend fun toggleFavourite(
        mediaId: Long,
        mediaType: String,
        name: String,
        uri: String,
        size: Long,
        dateAdded: Long
    ): Boolean
}