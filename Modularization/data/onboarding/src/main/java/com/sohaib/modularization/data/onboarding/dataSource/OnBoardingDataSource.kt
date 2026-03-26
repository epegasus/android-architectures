package com.sohaib.modularization.data.onboarding.dataSource

import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.data.onboarding.model.OnboardingEntity

class OnBoardingDataSource {

    fun getOnBoardingData(): List<OnboardingEntity> {
        return listOf(
            OnboardingEntity(
                id = 1,
                title = "Welcome to Modularization Sample",
                description = "Discover the power of modularized Android development with clean architecture",
                imageRes = R.drawable.img_png_on_boarding_1
            ),
            OnboardingEntity(
                id = 2,
                title = "Media Gallery",
                description = "Browse your photos and videos with advanced features and organization",
                imageRes = R.drawable.img_png_on_boarding_2
            ),
            OnboardingEntity(
                id = 3,
                title = "Favorites",
                description = "Save your favorite media items for quick access",
                imageRes = R.drawable.img_png_on_boarding_3
            ),
            OnboardingEntity(
                id = 4,
                title = "Settings & Preferences",
                description = "Customize your experience with personalized settings",
                imageRes = R.drawable.img_png_on_boarding_4
            )
        )
    }
}