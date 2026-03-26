package com.sohaib.modularization.feature.language.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohaib.modularization.core.theme.R
import com.sohaib.modularization.domain.language.entity.Language
import com.sohaib.modularization.domain.language.usecase.GetLanguagesUseCase
import com.sohaib.modularization.domain.language.usecase.SetLanguageUseCase
import com.sohaib.modularization.feature.language.intent.LanguageIntent
import com.sohaib.modularization.feature.language.states.LanguageDestination
import com.sohaib.modularization.feature.language.states.LanguageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for Language screen
 */
class LanguageViewModel(private val getLanguagesUseCase: GetLanguagesUseCase, private val setLanguageUseCase: SetLanguageUseCase) : ViewModel() {

    private val _states = MutableStateFlow(LanguageState())
    val states: StateFlow<LanguageState> = _states.asStateFlow()

    private var selectedLanguage: Language? = null

    init {
        handleIntent(LanguageIntent.LoadLanguages)
    }

    fun handleIntent(intent: LanguageIntent) = viewModelScope.launch {
        when (intent) {
            is LanguageIntent.LoadLanguages -> loadLanguages()
            is LanguageIntent.ContinueClicked -> onContinueClicked()
            is LanguageIntent.NavigationCleared -> _states.update { it.copy(navigateTo = null) }
            is LanguageIntent.ErrorCleared -> _states.update { it.copy(errorMessageRes = null) }
        }
    }

    private fun loadLanguages() = viewModelScope.launch {
        _states.update { it.copy(isLoading = true) }

        getLanguagesUseCase().collect { languages ->
            val list = languages.map { language ->
                language.copy(itemClick = { onLanguageSelected(language) })
            }

            _states.update { it.copy(isLoading = false, languages = list) }
        }
    }

    private fun onLanguageSelected(language: Language) {
        selectedLanguage = language

        // Update selected state in list
        val updatedList = _states.value.languages.map {
            it.copy(isSelected = it.code == language.code)
        }

        _states.update { it.copy(languages = updatedList) }
    }

    fun onContinueClicked() = viewModelScope.launch {
        val selected = selectedLanguage

        if (selected == null) {
            _states.update {
                it.copy(errorMessageRes = R.string.toast_no_language_selected)
            }
            return@launch
        }

        setLanguageUseCase(selected.code)

        _states.update {
            it.copy(navigateTo = LanguageDestination.OnBoarding)
        }
    }
}