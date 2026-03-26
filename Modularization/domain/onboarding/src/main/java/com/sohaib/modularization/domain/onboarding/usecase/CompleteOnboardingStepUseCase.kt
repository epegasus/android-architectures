package com.sohaib.modularization.domain.onboarding.usecase

import com.sohaib.modularization.domain.onboarding.repository.OnboardingRepository

/**
 * Use case for completing an onboarding step
 */
class CompleteOnboardingStepUseCase(private val onboardingRepository: OnboardingRepository) {

    suspend operator fun invoke() {
        onboardingRepository.completeOnboarding()
    }
}