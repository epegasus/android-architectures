package com.sohaib.modularization.data.videos.repository

import com.sohaib.modularization.core.mediastore.MediaStoreManager
import com.sohaib.modularization.core.mediastore.model.MediaType
import com.sohaib.modularization.data.videos.mapper.mapToDomain
import com.sohaib.modularization.domain.videos.entity.Video
import com.sohaib.modularization.domain.videos.repository.VideosRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * Implementation of VideosRepository
 */
class VideosRepositoryImpl(
    private val mediaStoreManager: MediaStoreManager,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : VideosRepository {

    override suspend fun getVideos(): Flow<List<Video>> {
        return mediaStoreManager
            .getVideos().map { mediaItems ->
                mediaItems.map { mediaItem ->
                    mediaItem.mapToDomain()
                }
            }
            .flowOn(defaultDispatcher)
    }

    override suspend fun getVideosPaginated(pageSize: Int, offset: Int): Flow<List<Video>> {
        return mediaStoreManager
            .getVideosPaginated(pageSize, offset).map { mediaItems ->
                mediaItems.map { mediaItem ->
                    mediaItem.mapToDomain()
                }
            }
            .flowOn(defaultDispatcher)
    }

    override suspend fun getVideoById(id: Long): Video? {
        val mediaDetail = mediaStoreManager.getMediaDetail(id, MediaType.VIDEO)
        return mediaDetail?.let { detail ->
            Video(
                id = detail.id,
                name = detail.name,
                uri = detail.uri,
                size = detail.size,
                dateAdded = detail.dateAdded,
                dateModified = detail.dateModified,
                mimeType = detail.mimeType,
                width = detail.width,
                height = detail.height,
                duration = detail.duration,
                bucketName = detail.bucketName,
                bucketId = detail.bucketId
            )
        }
    }

    override suspend fun searchVideos(query: String): Flow<List<Video>> {
        return getVideos().map { videos ->
            videos.filter { video ->
                video.name.contains(query, ignoreCase = true) ||
                        video.bucketName?.contains(query, ignoreCase = true) == true
            }
        }
    }

    override suspend fun getVideosByDateRange(startDate: Long, endDate: Long): Flow<List<Video>> {
        return getVideos().map { videos ->
            videos.filter { video ->
                video.dateAdded in startDate..endDate
            }
        }
    }

    override suspend fun getVideosByDurationRange(minDuration: Long, maxDuration: Long): Flow<List<Video>> {
        return getVideos().map { videos ->
            videos.filter { video ->
                video.duration in minDuration..maxDuration
            }
        }
    }

    override suspend fun getVideosBySizeRange(minSize: Long, maxSize: Long): Flow<List<Video>> {
        return getVideos().map { videos ->
            videos.filter { video ->
                video.size in minSize..maxSize
            }
        }
    }
}