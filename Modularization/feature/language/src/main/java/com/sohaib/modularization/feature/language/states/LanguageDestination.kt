package com.sohaib.modularization.feature.language.states

/**
 * Navigation events for Language screen
 */
sealed class LanguageDestination {
    object OnBoarding : LanguageDestination()
}