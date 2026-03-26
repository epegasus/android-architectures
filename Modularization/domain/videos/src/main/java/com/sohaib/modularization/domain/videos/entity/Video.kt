package com.sohaib.modularization.domain.videos.entity

import android.net.Uri

/**
 * Domain entity representing a video
 */
data class Video(
    val id: Long,
    val name: String,
    val uri: Uri,
    val size: Long,
    val dateAdded: Long,
    val dateModified: Long,
    val mimeType: String,
    val width: Int,
    val height: Int,
    val duration: Long,
    val isFavourite: Boolean = false,
    val bucketName: String? = null,
    val bucketId: String? = null,
    val itemClick: () -> Unit = {},
    val favouriteClick: () -> Unit = {},
)