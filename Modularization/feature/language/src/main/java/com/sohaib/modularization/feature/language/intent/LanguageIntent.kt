package com.sohaib.modularization.feature.language.intent

sealed class LanguageIntent {
    object LoadLanguages : LanguageIntent()
    object ContinueClicked : LanguageIntent()
    object NavigationCleared : LanguageIntent()
    object ErrorCleared : LanguageIntent()
}