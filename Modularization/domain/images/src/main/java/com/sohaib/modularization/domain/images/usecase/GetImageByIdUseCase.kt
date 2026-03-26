package com.sohaib.modularization.domain.images.usecase

import com.sohaib.modularization.domain.images.entity.Image
import com.sohaib.modularization.domain.images.repository.ImagesRepository

/**
 * Use case for getting an image by ID
 */
class GetImageByIdUseCase(private val imagesRepository: ImagesRepository) {

    suspend operator fun invoke(id: Long): Image? {
        return imagesRepository.getImageById(id)
    }
}