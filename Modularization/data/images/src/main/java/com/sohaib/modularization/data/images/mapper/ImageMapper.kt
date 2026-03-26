package com.sohaib.modularization.data.images.mapper

import com.sohaib.modularization.core.mediastore.model.MediaItem
import com.sohaib.modularization.domain.images.entity.Image

/**
 * Mapper for converting between data and domain layers
 */

fun MediaItem.mapToDomain(): Image {
    return Image(
        id = id,
        name = name,
        uri = uri,
        size = size,
        dateAdded = dateAdded,
        dateModified = dateModified,
        mimeType = mimeType,
        width = width,
        height = height
    )
}