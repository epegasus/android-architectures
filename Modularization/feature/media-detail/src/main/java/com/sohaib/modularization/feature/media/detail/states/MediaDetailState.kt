package com.sohaib.modularization.feature.media.detail.states

import com.sohaib.modularization.domain.media.detail.entity.MediaDetail

/**
 * State for Media Detail screen
 */
data class MediaDetailState(
    val isLoading: Boolean = false,
    val mediaDetail: MediaDetail? = null,
    val errorMessageRes: Int? = null,
    val navigateTo: MediaDetailDestination? = null
)