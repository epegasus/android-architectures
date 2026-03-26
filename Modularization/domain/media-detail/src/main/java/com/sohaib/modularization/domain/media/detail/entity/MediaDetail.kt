package com.sohaib.modularization.domain.media.detail.entity

import android.net.Uri

/**
 * Domain entity representing a media item for detail view
 */
data class MediaDetail(
    val id: Long,
    val name: String,
    val uri: Uri,
    val size: Long,
    val dateAdded: Long,
    val dateModified: Long,
    val mimeType: String,
    val width: Int,
    val height: Int,
    val duration: Long? = null, // Only for videos
    val bucketName: String? = null,
    val bucketId: String? = null,
    val mediaType: String, // "image" or "video"
    val isFavourite: Boolean = false,
    val itemClick: () -> Unit = {},
    val favouriteClick: () -> Unit = {},
    val shareClick: () -> Unit = {},
    val infoClick: () -> Unit = {}
)