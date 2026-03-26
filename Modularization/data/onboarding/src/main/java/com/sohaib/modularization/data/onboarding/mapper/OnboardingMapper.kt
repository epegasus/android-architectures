package com.sohaib.modularization.data.onboarding.mapper

import com.sohaib.modularization.data.onboarding.model.OnboardingEntity
import com.sohaib.modularization.domain.onboarding.entity.OnBoardingStep

/**
 * Mapper for converting between data and domain layers
 */
fun OnboardingEntity.mapToDomain(): OnBoardingStep {
    return OnBoardingStep(
        stepNumber = id,
        title = title,
        description = description,
        imageRes = imageRes
    )
}

fun OnBoardingStep.mapToData(): OnboardingEntity {
    return OnboardingEntity(
        id = stepNumber,
        title = title,
        description = description,
        imageRes = imageRes,
    )
}