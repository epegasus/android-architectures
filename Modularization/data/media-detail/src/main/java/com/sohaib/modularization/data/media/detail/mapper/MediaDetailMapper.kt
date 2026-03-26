package com.sohaib.modularization.data.media.detail.mapper

import com.sohaib.modularization.domain.images.entity.Image
import com.sohaib.modularization.domain.media.detail.entity.MediaDetail
import com.sohaib.modularization.domain.videos.entity.Video

/**
 * Extension function to map Image to MediaDetail domain entity
 */
fun Image.mapToMediaDetail(isFavourite: Boolean = false): MediaDetail {
    return MediaDetail(
        id = this.id,
        name = this.name,
        uri = this.uri,
        size = this.size,
        dateAdded = this.dateAdded,
        dateModified = this.dateModified,
        mimeType = this.mimeType,
        width = this.width,
        height = this.height,
        duration = null,
        bucketName = this.bucketName,
        bucketId = this.bucketId,
        mediaType = "image",
        isFavourite = isFavourite
    )
}

/**
 * Extension function to map Video to MediaDetail domain entity
 */
fun Video.mapToMediaDetail(isFavourite: Boolean = false): MediaDetail {
    return MediaDetail(
        id = this.id,
        name = this.name,
        uri = this.uri,
        size = this.size,
        dateAdded = this.dateAdded,
        dateModified = this.dateModified,
        mimeType = this.mimeType,
        width = this.width,
        height = this.height,
        duration = this.duration,
        bucketName = this.bucketName,
        bucketId = this.bucketId,
        mediaType = "video",
        isFavourite = isFavourite
    )
}