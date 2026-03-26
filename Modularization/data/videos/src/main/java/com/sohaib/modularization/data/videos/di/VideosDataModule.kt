package com.sohaib.modularization.data.videos.di

import com.sohaib.modularization.data.videos.repository.VideosRepositoryImpl
import com.sohaib.modularization.domain.videos.repository.VideosRepository
import org.koin.dsl.module

/**
 * Koin module for Videos data layer
 */
val videosDataModule = module {
    single<VideosRepository> { VideosRepositoryImpl(get()) }
}