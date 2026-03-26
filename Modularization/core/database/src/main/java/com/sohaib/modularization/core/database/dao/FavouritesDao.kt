package com.sohaib.modularization.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sohaib.modularization.core.database.entity.FavouriteEntity
import com.sohaib.modularization.core.database.entity.MediaType
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for favourites
 */
@Dao
interface FavouritesDao {

    /**
     * Get all favourites ordered by date favourited
     */
    @Query("SELECT * FROM favourites ORDER BY dateFavourited DESC")
    fun getAllFavourites(): Flow<List<FavouriteEntity>>

    /**
     * Get favourites by media type
     */
    @Query("SELECT * FROM favourites WHERE mediaType = :mediaType ORDER BY dateFavourited DESC")
    fun getFavouritesByType(mediaType: MediaType): Flow<List<FavouriteEntity>>

    /**
     * Get a specific favourite by ID
     */
    @Query("SELECT * FROM favourites WHERE id = :id")
    suspend fun getFavouriteById(id: Long): FavouriteEntity?

    /**
     * Insert a favourite
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: FavouriteEntity)

    /**
     * Delete a favourite
     */
    @Delete
    suspend fun deleteFavourite(favourite: FavouriteEntity)

    /**
     * Delete favourite by ID
     */
    @Query("DELETE FROM favourites WHERE id = :id")
    suspend fun deleteFavouriteById(id: Long)

    /**
     * Check if an item is favourite
     */
    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE id = :id)")
    suspend fun isFavourite(id: Long): Boolean

    /**
     * Get favourite statuses for multiple media items in batch
     */
    @Query("SELECT id FROM favourites WHERE id IN (:mediaIds)")
    suspend fun getFavouriteIds(mediaIds: List<Long>): List<Long>

    /**
     * Get favourite count
     */
    @Query("SELECT COUNT(*) FROM favourites")
    suspend fun getFavouriteCount(): Int

    /**
     * Get favourite count by type
     */
    @Query("SELECT COUNT(*) FROM favourites WHERE mediaType = :mediaType")
    suspend fun getFavouriteCountByType(mediaType: MediaType): Int

    /**
     * Clear all favourites
     */
    @Query("DELETE FROM favourites")
    suspend fun clearAllFavourites()

    /**
     * Search favourites by name
     */
    @Query("SELECT * FROM favourites WHERE name LIKE :query ORDER BY dateFavourited DESC")
    fun searchFavourites(query: String): Flow<List<FavouriteEntity>>
}