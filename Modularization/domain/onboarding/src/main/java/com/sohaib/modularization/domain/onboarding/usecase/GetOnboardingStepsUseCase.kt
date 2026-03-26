package com.sohaib.modularization.domain.onboarding.usecase

import com.sohaib.modularization.domain.onboarding.entity.OnBoardingStep
import com.sohaib.modularization.domain.onboarding.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting onboarding steps
 */
class GetOnboardingStepsUseCase(private val onboardingRepository: OnboardingRepository) {

    suspend operator fun invoke(): Flow<List<OnBoardingStep>> {
        return onboardingRepository.getOnboardingSteps()
    }
}