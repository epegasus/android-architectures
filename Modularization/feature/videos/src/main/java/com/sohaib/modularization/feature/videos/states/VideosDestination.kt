package com.sohaib.modularization.feature.videos.states

import com.sohaib.modularization.domain.videos.entity.Video

/**
 * Navigation destinations for Videos screen
 */
sealed class VideosDestination {
    data class VideoDetail(val video: Video) : VideosDestination()
    object Search : VideosDestination()
}