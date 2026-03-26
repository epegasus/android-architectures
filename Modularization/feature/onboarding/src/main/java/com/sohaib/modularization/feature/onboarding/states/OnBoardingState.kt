package com.sohaib.modularization.feature.onboarding.states

import com.sohaib.modularization.domain.onboarding.entity.OnBoardingStep

/**
 * Navigation events for Onboarding screen
 */

data class OnBoardingState(
    val isLoading: Boolean = false,
    val steps: List<OnBoardingStep> = emptyList(),
    val currentStep: Int = 0,
    val progressPercent: Int = 0,
    val navigateTo: OnBoardingDestination? = null
)