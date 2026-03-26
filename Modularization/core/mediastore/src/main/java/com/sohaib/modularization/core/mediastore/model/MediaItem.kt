package com.sohaib.modularization.core.mediastore.model

import android.net.Uri

/**
 * Data class representing a media item from MediaStore
 */
data class MediaItem(
    val id: Long,
    val name: String,
    val uri: Uri,
    val size: Long,
    val dateAdded: Long,
    val dateModified: Long,
    val mimeType: String,
    val mediaType: MediaType,
    val width: Int = 0,
    val height: Int = 0,
    val duration: Long = 0L // For videos
)