package com.sohaib.modularization.feature.images.states

import com.sohaib.modularization.domain.images.entity.Image

data class ImagesState(
    val isLoading: Boolean = false,
    val images: List<Image> = emptyList(),
    val errorMessageRes: Int? = null,
    val navigateTo: ImagesDestination? = null
)