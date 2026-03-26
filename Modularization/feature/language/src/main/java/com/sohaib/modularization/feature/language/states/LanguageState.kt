package com.sohaib.modularization.feature.language.states

import com.sohaib.modularization.domain.language.entity.Language

data class LanguageState(
    val isLoading: Boolean = false,
    val languages: List<Language> = emptyList(),
    val navigateTo: LanguageDestination? = null,
    val errorMessageRes: Int? = null
)