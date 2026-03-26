package com.sohaib.modularization.data.onboarding.di

import com.sohaib.modularization.data.onboarding.dataSource.OnBoardingDataSource
import com.sohaib.modularization.data.onboarding.repository.OnboardingRepositoryImpl
import com.sohaib.modularization.domain.onboarding.repository.OnboardingRepository
import org.koin.dsl.module

/**
 * Koin module for Onboarding data layer
 */
val onboardingDataModule = module {
    single { OnBoardingDataSource() }
    single<OnboardingRepository> { OnboardingRepositoryImpl(get(), get(), get()) }
}