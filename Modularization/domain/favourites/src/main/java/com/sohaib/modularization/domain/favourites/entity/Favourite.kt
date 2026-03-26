package com.sohaib.modularization.domain.favourites.entity

import android.net.Uri

/**
 * Domain entity representing a favourite item
 */
data class Favourite(
    val id: Long,
    val mediaType: String, // "image" or "video"
    val name: String,
    val uri: Uri,
    val size: Long,
    val dateAdded: Long,
    val dateFavourited: Long,
    val itemClick: () -> Unit = {},
    val favouriteClick: () -> Unit = {},
)