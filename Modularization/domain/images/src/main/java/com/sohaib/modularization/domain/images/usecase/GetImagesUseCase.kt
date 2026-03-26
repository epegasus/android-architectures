package com.sohaib.modularization.domain.images.usecase

import com.sohaib.modularization.domain.images.entity.Image
import com.sohaib.modularization.domain.images.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting all images
 */
class GetImagesUseCase(private val imagesRepository: ImagesRepository) {

    suspend operator fun invoke(): Flow<List<Image>> {
        return imagesRepository.getImages()
    }
}