package com.sohaib.modularization.data.entrance.di

import com.sohaib.modularization.data.entrance.repository.EntranceRepositoryImpl
import com.sohaib.modularization.domain.entrance.repository.EntranceRepository
import org.koin.dsl.module

/**
 * Koin module for Language data layer
 */
val entranceDataModule = module {
    single<EntranceRepository> { EntranceRepositoryImpl(get(), get()) }
}