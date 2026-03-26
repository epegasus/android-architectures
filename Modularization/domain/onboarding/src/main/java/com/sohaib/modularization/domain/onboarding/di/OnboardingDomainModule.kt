package com.sohaib.modularization.domain.onboarding.di

import com.sohaib.modularization.domain.onboarding.usecase.CompleteOnboardingStepUseCase
import com.sohaib.modularization.domain.onboarding.usecase.GetOnboardingStepsUseCase
import org.koin.dsl.module

/**
 * Koin module for Onboarding domain layer
 */
val onboardingDomainModule = module {
    factory { GetOnboardingStepsUseCase(get()) }
    factory { CompleteOnboardingStepUseCase(get()) }
}