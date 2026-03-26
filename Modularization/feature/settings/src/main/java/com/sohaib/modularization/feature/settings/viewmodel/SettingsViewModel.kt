package com.sohaib.modularization.feature.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.domain.settings.entity.Settings
import com.sohaib.modularization.domain.settings.usecase.GetSettingsUseCase
import com.sohaib.modularization.domain.settings.usecase.ResetSettingsUseCase
import com.sohaib.modularization.domain.settings.usecase.UpdateSettingsUseCase
import com.sohaib.modularization.feature.settings.intent.SettingsIntent
import com.sohaib.modularization.feature.settings.states.SettingsDestination
import com.sohaib.modularization.feature.settings.states.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for Settings screen
 */
class SettingsViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase,
    private val resetSettingsUseCase: ResetSettingsUseCase
) : ViewModel() {

    private val _states = MutableStateFlow(SettingsState())
    val states: StateFlow<SettingsState> = _states.asStateFlow()

    init {
        handleIntent(SettingsIntent.LoadSettings)
    }

    fun handleIntent(intent: SettingsIntent) = viewModelScope.launch {
        when (intent) {
            is SettingsIntent.LoadSettings -> loadSettings()
            is SettingsIntent.RefreshSettings -> loadSettings()
            is SettingsIntent.UpdateTheme -> updateTheme(intent.theme)
            is SettingsIntent.UpdateLanguage -> updateLanguage(intent.language)
            is SettingsIntent.UpdateNotifications -> updateNotifications(intent.enabled)
            is SettingsIntent.UpdateAutoPlay -> updateAutoPlay(intent.enabled)
            is SettingsIntent.UpdateImageQuality -> updateImageQuality(intent.quality)
            is SettingsIntent.UpdateVideoQuality -> updateVideoQuality(intent.quality)
            is SettingsIntent.ClearCache -> clearCache()
            is SettingsIntent.ResetSettings -> resetSettings()
            is SettingsIntent.NavigationCleared -> _states.update { it.copy(navigateTo = null) }
            is SettingsIntent.ErrorCleared -> _states.update { it.copy(errorMessageRes = null) }
        }
    }

    private fun loadSettings() = viewModelScope.launch {
        _states.update { it.copy(isLoading = true) }

        getSettingsUseCase()
            .catch {
                _states.update { it.copy(isLoading = false, errorMessageRes = R.string.toast_something_went_wrong) }
            }
            .collect { settings ->
                val updatedSettings = settings.copy(
                    itemClick = { onSettingClicked(settings) },
                    settingClick = { onSettingClicked(settings) }
                )
                _states.update { it.copy(isLoading = false, settings = updatedSettings) }
            }
    }

    private fun updateTheme(theme: String) = viewModelScope.launch {
        try {
            val currentSettings = _states.value.settings
            val updatedSettings = currentSettings.copy(theme = theme)
            updateSettingsUseCase(updatedSettings)
            _states.update { it.copy(settings = updatedSettings, navigateTo = SettingsDestination.ThemeChanged) }
        } catch (e: Exception) {
            _states.update { it.copy(errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun updateLanguage(language: String) = viewModelScope.launch {
        try {
            val currentSettings = _states.value.settings
            val updatedSettings = currentSettings.copy(language = language)
            updateSettingsUseCase(updatedSettings)
            _states.update { it.copy(settings = updatedSettings, navigateTo = SettingsDestination.LanguageChanged) }
        } catch (e: Exception) {
            _states.update { it.copy(errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun updateNotifications(enabled: Boolean) = viewModelScope.launch {
        try {
            val currentSettings = _states.value.settings
            val updatedSettings = currentSettings.copy(notifications = enabled)
            updateSettingsUseCase(updatedSettings)
            _states.update { it.copy(settings = updatedSettings) }
        } catch (e: Exception) {
            _states.update { it.copy(errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun updateAutoPlay(enabled: Boolean) = viewModelScope.launch {
        try {
            val currentSettings = _states.value.settings
            val updatedSettings = currentSettings.copy(autoPlay = enabled)
            updateSettingsUseCase(updatedSettings)
            _states.update { it.copy(settings = updatedSettings) }
        } catch (e: Exception) {
            _states.update { it.copy(errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun updateImageQuality(quality: String) = viewModelScope.launch {
        try {
            val currentSettings = _states.value.settings
            val updatedSettings = currentSettings.copy(imageQuality = quality)
            updateSettingsUseCase(updatedSettings)
            _states.update { it.copy(settings = updatedSettings) }
        } catch (e: Exception) {
            _states.update { it.copy(errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun updateVideoQuality(quality: String) = viewModelScope.launch {
        try {
            val currentSettings = _states.value.settings
            val updatedSettings = currentSettings.copy(videoQuality = quality)
            updateSettingsUseCase(updatedSettings)
            _states.update { it.copy(settings = updatedSettings) }
        } catch (e: Exception) {
            _states.update { it.copy(errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun clearCache() = viewModelScope.launch {
        try {
            val currentSettings = _states.value.settings
            val updatedSettings = currentSettings.copy(cacheSize = 0L)
            updateSettingsUseCase(updatedSettings)
            _states.update { it.copy(settings = updatedSettings, navigateTo = SettingsDestination.CacheCleared) }
        } catch (e: Exception) {
            _states.update { it.copy(errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun resetSettings() = viewModelScope.launch {
        try {
            resetSettingsUseCase()
            _states.update { it.copy(navigateTo = SettingsDestination.SettingsReset) }
        } catch (e: Exception) {
            _states.update { it.copy(errorMessageRes = R.string.toast_something_went_wrong) }
        }
    }

    private fun onSettingClicked(settings: Settings) = viewModelScope.launch {
        // Handle setting click if needed
    }
}