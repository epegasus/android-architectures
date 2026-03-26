package com.sohaib.modularization.data.videos.mapper

import com.sohaib.modularization.core.mediastore.model.MediaItem
import com.sohaib.modularization.domain.videos.entity.Video

/**
 * Extension function to map MediaItem to Video domain entity
 */
fun MediaItem.mapToDomain(): Video {
    return Video(
        id = this.id,
        name = this.name,
        uri = this.uri,
        size = this.size,
        dateAdded = this.dateAdded,
        dateModified = this.dateModified,
        mimeType = this.mimeType,
        width = this.width,
        height = this.height,
        duration = this.duration
    )
}