package com.sohaib.modularization.domain.images.entity

import android.net.Uri

/**
 * Domain entity representing an image
 */
data class Image(
    val id: Long,
    val name: String,
    val uri: Uri,
    val size: Long,
    val dateAdded: Long,
    val dateModified: Long,
    val mimeType: String,
    val width: Int,
    val height: Int,
    val isFavourite: Boolean = false,
    val bucketName: String? = null,
    val bucketId: String? = null,
    val itemClick: () -> Unit = {},
    val favouriteClick: () -> Unit = {},
)