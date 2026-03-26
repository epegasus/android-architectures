package com.sohaib.modularization.domain.videos.di

import com.sohaib.modularization.domain.videos.usecase.GetVideoByIdUseCase
import com.sohaib.modularization.domain.videos.usecase.GetVideosPaginatedUseCase
import com.sohaib.modularization.domain.videos.usecase.GetVideosUseCase
import com.sohaib.modularization.domain.videos.usecase.SearchVideosUseCase
import org.koin.dsl.module

/**
 * Koin module for Videos domain layer
 */
val videosDomainModule = module {
    factory { GetVideosUseCase(get()) }
    factory { GetVideosPaginatedUseCase(get()) }
    factory { GetVideoByIdUseCase(get()) }
    factory { SearchVideosUseCase(get()) }
}