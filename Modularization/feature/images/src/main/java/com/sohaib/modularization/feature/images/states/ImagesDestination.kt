package com.sohaib.modularization.feature.images.states

import com.sohaib.modularization.domain.images.entity.Image

sealed class ImagesDestination {
    data class ImageDetail(val image: Image) : ImagesDestination()
    object Search : ImagesDestination()
}