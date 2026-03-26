package com.sohaib.modularization.feature.onboarding.intent

sealed class OnBoardingIntent {
    object LoadTutorials : OnBoardingIntent()
    data class PageChanged(val position: Int) : OnBoardingIntent()
    object NextClicked : OnBoardingIntent()
    object SkipClicked : OnBoardingIntent()
    object CompleteClicked : OnBoardingIntent()
    object NavigationCleared : OnBoardingIntent()
}