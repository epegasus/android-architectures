package com.sohaib.modularization.feature.settings.states

import com.sohaib.modularization.domain.settings.entity.Settings

/**
 * State for Settings screen
 */
data class SettingsState(
    val isLoading: Boolean = false,
    val settings: Settings = Settings(),
    val errorMessageRes: Int? = null,
    val navigateTo: SettingsDestination? = null
)