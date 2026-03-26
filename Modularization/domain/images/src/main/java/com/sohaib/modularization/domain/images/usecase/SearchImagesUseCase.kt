package com.sohaib.modularization.domain.images.usecase

import com.sohaib.modularization.domain.images.entity.Image
import com.sohaib.modularization.domain.images.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for searching images
 */
class SearchImagesUseCase(private val imagesRepository: ImagesRepository) {

    suspend operator fun invoke(query: String): Flow<List<Image>> {
        return imagesRepository.searchImages(query)
    }
}