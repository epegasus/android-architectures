package com.sohaib.modularization.domain.media.detail.di

import com.sohaib.modularization.domain.media.detail.usecase.GetMediaDetailUseCase
import com.sohaib.modularization.domain.media.detail.usecase.IsMediaFavouriteUseCase
import com.sohaib.modularization.domain.media.detail.usecase.ToggleMediaFavouriteUseCase
import org.koin.dsl.module

/**
 * Koin module for Media Detail domain layer
 */
val mediaDetailDomainModule = module {
    factory { GetMediaDetailUseCase(get()) }
    factory { IsMediaFavouriteUseCase(get()) }
    factory { ToggleMediaFavouriteUseCase(get()) }
}
