package com.sohaib.modularization.data.onboarding.model

/**
 * Data class representing an onboarding step
 */
data class OnboardingEntity(
    val id: Int,
    val title: String,
    val description: String,
    val imageRes: Int
)