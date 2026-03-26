package com.sohaib.modularization.data.media.detail.repository

import com.sohaib.modularization.data.media.detail.mapper.mapToMediaDetail
import com.sohaib.modularization.domain.favourites.usecase.IsFavouriteUseCase
import com.sohaib.modularization.domain.favourites.usecase.ToggleFavouriteUseCase
import com.sohaib.modularization.domain.images.usecase.GetImageByIdUseCase
import com.sohaib.modularization.domain.media.detail.entity.MediaDetail
import com.sohaib.modularization.domain.media.detail.repository.MediaDetailRepository
import com.sohaib.modularization.domain.videos.usecase.GetVideoByIdUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of MediaDetailRepository
 */
class MediaDetailRepositoryImpl(
    private val getImageByIdUseCase: GetImageByIdUseCase,
    private val getVideoByIdUseCase: GetVideoByIdUseCase,
    private val isFavouriteUseCase: IsFavouriteUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MediaDetailRepository {

    override suspend fun getMediaDetail(mediaId: Long, mediaType: String): MediaDetail? = withContext(defaultDispatcher) {
        when (mediaType.lowercase()) {
            "image" -> {
                val image = getImageByIdUseCase(mediaId)
                val isFavourite = isFavouriteUseCase(mediaId)
                image?.mapToMediaDetail(isFavourite)
            }

            "video" -> {
                val video = getVideoByIdUseCase(mediaId)
                val isFavourite = isFavouriteUseCase(mediaId)
                video?.mapToMediaDetail(isFavourite)
            }

            else -> null
        }
    }

    override suspend fun isFavourite(mediaId: Long): Boolean {
        return isFavouriteUseCase(mediaId)
    }

    override suspend fun toggleFavourite(
        mediaId: Long,
        mediaType: String,
        name: String,
        uri: String,
        size: Long,
        dateAdded: Long
    ): Boolean {
        return toggleFavouriteUseCase(mediaId, mediaType, name, uri, size, dateAdded)
    }
}