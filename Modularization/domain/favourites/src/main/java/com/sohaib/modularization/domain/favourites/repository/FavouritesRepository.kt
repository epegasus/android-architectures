package com.sohaib.modularization.domain.favourites.repository

import com.sohaib.modularization.domain.favourites.entity.Favourite
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for favourites data operations
 */
interface FavouritesRepository {

    /**
     * Get all favourites
     */
    suspend fun getFavourites(): Flow<List<Favourite>>

    /**
     * Get favourites by media type
     */
    suspend fun getFavouritesByType(mediaType: String): Flow<List<Favourite>>

    /**
     * Get favourite by ID
     */
    suspend fun getFavouriteById(id: Long): Favourite?

    /**
     * Search favourites by name
     */
    suspend fun searchFavourites(query: String): Flow<List<Favourite>>

    /**
     * Get favourites by date range
     */
    suspend fun getFavouritesByDateRange(startDate: Long, endDate: Long): Flow<List<Favourite>>

    /**
     * Get favourites by size range
     */
    suspend fun getFavouritesBySizeRange(minSize: Long, maxSize: Long): Flow<List<Favourite>>

    /**
     * Toggle favourite status
     */
    suspend fun toggleFavourite(mediaId: Long, mediaType: String, name: String, uri: String, size: Long, dateAdded: Long): Boolean

    /**
     * Check if item is favourite
     */
    suspend fun isFavourite(id: Long): Boolean

    /**
     * Get favourite statuses for multiple media items in batch
     */
    suspend fun getFavouriteStatuses(mediaIds: List<Long>): Map<Long, Boolean>

    /**
     * Get favourite count
     */
    suspend fun getFavouriteCount(): Int

    /**
     * Get favourite count by type
     */
    suspend fun getFavouriteCountByType(mediaType: String): Int

    /**
     * Clear all favourites
     */
    suspend fun clearAllFavourites()
}