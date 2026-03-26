package com.sohaib.modularization.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.sohaib.modularization.R
import com.sohaib.modularization.core.navigation.AppNavigator
import com.sohaib.modularization.feature.media.detail.ui.MediaDetailFragment

class AppNavigatorImpl(private val activity: AppCompatActivity) : AppNavigator {

    private val navController get() = activity.findNavController(R.id.fcvContainerMain)

    override fun openLanguageFromEntrance() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.sohaib.modularization/language".toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.entranceFragment, true)
            .build()
        navController.navigate(request, navOptions)
    }

    override fun openOnBoardingFromEntrance() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.sohaib.modularization/onboarding".toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.entranceFragment, true)
            .build()
        navController.navigate(request, navOptions)
    }

    override fun openDashboardFromEntrance() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.sohaib.modularization/dashboard".toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.entranceFragment, true)
            .build()
        navController.navigate(request, navOptions)
    }

    override fun openOnBoardingFromLanguage() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.sohaib.modularization/onboarding".toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.languageFragment, true)
            .build()
        navController.navigate(request, navOptions)
    }

    override fun openDashboardFromOnboarding() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.sohaib.modularization/dashboard".toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.onboardingFragment, true)
            .build()
        navController.navigate(request, navOptions)
    }

    override fun openMediaDetail(mediaId: Long, mediaType: String) {
        val fragment = MediaDetailFragment.newInstance(mediaId, mediaType)
        navController.navigate(R.id.mediaDetailFragment, fragment.arguments)
    }
}