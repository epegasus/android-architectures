package com.sohaib.modularization.data.favourites.mapper

import androidx.core.net.toUri
import com.sohaib.modularization.core.database.entity.FavouriteEntity
import com.sohaib.modularization.domain.favourites.entity.Favourite

/**
 * Extension function to map FavouriteEntity to Favourite domain entity
 */
fun FavouriteEntity.mapToDomain(): Favourite {
    return Favourite(
        id = this.id,
        mediaType = this.mediaType.name.lowercase(),
        name = this.name,
        uri = this.uri.toUri(),
        size = this.size,
        dateAdded = this.dateAdded,
        dateFavourited = this.dateFavourited.time
    )
}