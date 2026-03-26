package com.sohaib.modularization.feature.onboarding.di

import com.sohaib.modularization.feature.onboarding.viewModel.OnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for Onboarding feature
 */
val onboardingFeatureModule = module {
    viewModel { OnboardingViewModel(get(), get()) }
}