package com.sohaib.modularization.domain.images.di

import com.sohaib.modularization.domain.images.usecase.GetImageByIdUseCase
import com.sohaib.modularization.domain.images.usecase.GetImagesUseCase
import com.sohaib.modularization.domain.images.usecase.SearchImagesUseCase
import org.koin.dsl.module

/**
 * Koin module for Images domain layer
 */
val imagesDomainModule = module {
    factory { GetImagesUseCase(get()) }
    factory { GetImageByIdUseCase(get()) }
    factory { SearchImagesUseCase(get()) }
}