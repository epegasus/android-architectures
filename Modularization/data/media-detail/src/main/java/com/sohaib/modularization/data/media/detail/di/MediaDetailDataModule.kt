package com.sohaib.modularization.data.media.detail.di

import com.sohaib.modularization.data.media.detail.repository.MediaDetailRepositoryImpl
import com.sohaib.modularization.domain.media.detail.repository.MediaDetailRepository
import org.koin.dsl.module

/**
 * Koin module for Media Detail data layer
 */
val mediaDetailDataModule = module {
    single<MediaDetailRepository> {
        MediaDetailRepositoryImpl(
            getImageByIdUseCase = get(),
            getVideoByIdUseCase = get(),
            isFavouriteUseCase = get(),
            toggleFavouriteUseCase = get(),
            defaultDispatcher = get()
        )
    }
}