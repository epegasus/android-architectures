package com.sohaib.modularization.core.mediastore.model

import android.net.Uri

/**
 * Data class representing media detail information
 */
data class MediaDetail(
    val id: Long,
    val name: String,
    val uri: Uri,
    val size: Long,
    val dateAdded: Long,
    val dateModified: Long,
    val mimeType: String,
    val mediaType: MediaType,
    val width: Int,
    val height: Int,
    val duration: Long,
    val bucketName: String? = null,
    val bucketId: String? = null,
    val displayName: String? = null,
    val title: String? = null,
    val description: String? = null
)