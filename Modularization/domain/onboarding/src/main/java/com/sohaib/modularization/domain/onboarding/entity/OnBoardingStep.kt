package com.sohaib.modularization.domain.onboarding.entity

/**
 * Domain entity representing an onboarding step
 */
data class OnBoardingStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val imageRes: Int
)