package com.sohaib.modularization.domain.images.usecase

import com.sohaib.modularization.domain.images.entity.Image
import com.sohaib.modularization.domain.images.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting images with pagination
 */
class GetImagesPaginatedUseCase(private val imagesRepository: ImagesRepository) {

    suspend operator fun invoke(
        pageSize: Int = 50,
        offset: Int = 0
    ): Flow<List<Image>> {
        return imagesRepository.getImagesPaginated(pageSize, offset)
    }
}
