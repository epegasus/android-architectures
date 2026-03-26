package com.sohaib.modularization.data.images.di

import com.sohaib.modularization.data.images.repository.ImagesRepositoryImpl
import com.sohaib.modularization.domain.images.repository.ImagesRepository
import org.koin.dsl.module

/**
 * Koin module for Images data layer
 */
val imagesDataModule = module {
    single<ImagesRepository> { ImagesRepositoryImpl(get(), get()) }
}