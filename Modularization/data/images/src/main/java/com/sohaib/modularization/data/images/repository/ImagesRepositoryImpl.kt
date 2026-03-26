package com.sohaib.modularization.data.images.repository

import com.sohaib.modularization.core.mediastore.MediaStoreManager
import com.sohaib.modularization.core.mediastore.model.MediaType
import com.sohaib.modularization.data.images.mapper.mapToDomain
import com.sohaib.modularization.domain.images.entity.Image
import com.sohaib.modularization.domain.images.repository.ImagesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Implementation of ImagesRepository
 */
class ImagesRepositoryImpl(
    private val mediaStoreManager: MediaStoreManager,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ImagesRepository {

    override suspend fun getImages(): Flow<List<Image>> {
        return mediaStoreManager
            .getImages().map { mediaItems ->
                mediaItems.map { mediaItem ->
                    mediaItem.mapToDomain()
                }
            }
            .flowOn(defaultDispatcher)
    }

    override suspend fun getImagesPaginated(pageSize: Int, offset: Int): Flow<List<Image>> {
        return mediaStoreManager
            .getImagesPaginated(pageSize, offset).map { mediaItems ->
                mediaItems.map { mediaItem ->
                    mediaItem.mapToDomain()
                }
            }
            .flowOn(defaultDispatcher)
    }

    override suspend fun getImageById(id: Long): Image? {
        val mediaDetail = mediaStoreManager.getMediaDetail(id, MediaType.IMAGE)
        return mediaDetail?.let { detail ->
            Image(
                id = detail.id,
                name = detail.name,
                uri = detail.uri,
                size = detail.size,
                dateAdded = detail.dateAdded,
                dateModified = detail.dateModified,
                mimeType = detail.mimeType,
                width = detail.width,
                height = detail.height,
                bucketName = detail.bucketName,
                bucketId = detail.bucketId
            )
        }
    }

    override suspend fun searchImages(query: String): Flow<List<Image>> {
        return getImages().map { images ->
            images.filter { image ->
                image.name.contains(query, ignoreCase = true) ||
                        image.bucketName?.contains(query, ignoreCase = true) == true
            }
        }
    }

    override suspend fun getImagesByDateRange(startDate: Long, endDate: Long): Flow<List<Image>> {
        return getImages().map { images ->
            images.filter { image ->
                image.dateAdded in startDate..endDate
            }
        }
    }

    override suspend fun getImagesBySizeRange(minSize: Long, maxSize: Long): Flow<List<Image>> {
        return getImages().map { images ->
            images.filter { image ->
                image.size in minSize..maxSize
            }
        }
    }
}