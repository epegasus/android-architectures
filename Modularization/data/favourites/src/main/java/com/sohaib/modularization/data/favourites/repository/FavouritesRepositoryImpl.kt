package com.sohaib.modularization.data.favourites.repository

import com.sohaib.modularization.core.database.dao.FavouritesDao
import com.sohaib.modularization.core.database.entity.FavouriteEntity
import com.sohaib.modularization.core.database.entity.MediaType
import com.sohaib.modularization.data.favourites.mapper.mapToDomain
import com.sohaib.modularization.domain.favourites.entity.Favourite
import com.sohaib.modularization.domain.favourites.repository.FavouritesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Implementation of FavouritesRepository
 */
class FavouritesRepositoryImpl(
    private val favouritesDao: FavouritesDao,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : FavouritesRepository {

    override suspend fun getFavourites(): Flow<List<Favourite>> {
        return favouritesDao.getAllFavourites().map { favouriteEntities ->
            favouriteEntities.map { it.mapToDomain() }
        }.flowOn(defaultDispatcher)
    }

    override suspend fun getFavouritesByType(mediaType: String): Flow<List<Favourite>> {
        return favouritesDao.getFavouritesByType(MediaType.valueOf(mediaType.uppercase())).map { favouriteEntities ->
            favouriteEntities.map { it.mapToDomain() }
        }.flowOn(defaultDispatcher)
    }

    override suspend fun getFavouriteById(id: Long): Favourite? {
        return favouritesDao.getFavouriteById(id)?.mapToDomain()
    }

    override suspend fun searchFavourites(query: String): Flow<List<Favourite>> {
        return favouritesDao.searchFavourites("%$query%").map { favouriteEntities ->
            favouriteEntities.map { it.mapToDomain() }
        }.flowOn(defaultDispatcher)
    }

    override suspend fun getFavouritesByDateRange(startDate: Long, endDate: Long): Flow<List<Favourite>> {
        return getFavourites().map { favourites ->
            favourites.filter { favourite ->
                favourite.dateAdded in startDate..endDate
            }
        }
    }

    override suspend fun getFavouritesBySizeRange(minSize: Long, maxSize: Long): Flow<List<Favourite>> {
        return getFavourites().map { favourites ->
            favourites.filter { favourite ->
                favourite.size in minSize..maxSize
            }
        }
    }

    override suspend fun toggleFavourite(
        mediaId: Long,
        mediaType: String,
        name: String,
        uri: String,
        size: Long,
        dateAdded: Long
    ): Boolean {
        val isCurrentlyFavourite = favouritesDao.isFavourite(mediaId)

        if (isCurrentlyFavourite) {
            favouritesDao.deleteFavouriteById(mediaId)
            return false
        } else {
            val favourite = FavouriteEntity(
                id = mediaId,
                mediaType = MediaType.valueOf(mediaType.uppercase()),
                name = name,
                uri = uri,
                size = size,
                dateAdded = dateAdded
            )
            favouritesDao.insertFavourite(favourite)
            return true
        }
    }

    override suspend fun isFavourite(id: Long): Boolean {
        return favouritesDao.isFavourite(id)
    }

    override suspend fun getFavouriteStatuses(mediaIds: List<Long>): Map<Long, Boolean> {
        val favouriteIds = favouritesDao.getFavouriteIds(mediaIds)
        return mediaIds.associateWith { id -> id in favouriteIds }
    }

    override suspend fun getFavouriteCount(): Int {
        return favouritesDao.getFavouriteCount()
    }

    override suspend fun getFavouriteCountByType(mediaType: String): Int {
        return favouritesDao.getFavouriteCountByType(MediaType.valueOf(mediaType.uppercase()))
    }

    override suspend fun clearAllFavourites() {
        favouritesDao.clearAllFavourites()
    }
}