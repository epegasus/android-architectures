package com.sohaib.modularization.domain.onboarding.repository

import com.sohaib.modularization.domain.onboarding.entity.OnBoardingStep
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for onboarding data operations
 */
interface OnboardingRepository {

    /**
     * Get all onboarding steps
     */
    suspend fun getOnboardingSteps(): Flow<List<OnBoardingStep>>

    /**
     * Complete onboarding
     */
    suspend fun completeOnboarding()
}