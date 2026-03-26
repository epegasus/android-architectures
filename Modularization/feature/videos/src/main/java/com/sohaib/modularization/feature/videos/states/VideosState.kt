package com.sohaib.modularization.feature.videos.states

import com.sohaib.modularization.domain.videos.entity.Video

/**
 * State for Videos screen
 */
data class VideosState(
    val isLoading: Boolean = false,
    val videos: List<Video> = emptyList(),
    val errorMessageRes: Int? = null,
    val navigateTo: VideosDestination? = null
)