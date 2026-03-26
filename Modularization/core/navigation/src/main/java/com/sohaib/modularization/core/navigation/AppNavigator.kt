package com.sohaib.modularization.core.navigation

interface AppNavigator {
    fun openLanguageFromEntrance()
    fun openOnBoardingFromEntrance()
    fun openDashboardFromEntrance()
    fun openOnBoardingFromLanguage()
    fun openDashboardFromOnboarding()
    fun openMediaDetail(mediaId: Long, mediaType: String)
}