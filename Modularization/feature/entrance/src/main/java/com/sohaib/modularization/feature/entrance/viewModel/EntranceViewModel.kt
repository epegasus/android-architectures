package com.sohaib.modularization.feature.entrance.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohaib.modularization.domain.entrance.useCase.GetUserPreferencesEntranceUseCase
import com.sohaib.modularization.feature.entrance.intent.EntranceIntent
import com.sohaib.modularization.feature.entrance.states.EntranceDestination
import com.sohaib.modularization.feature.entrance.states.EntranceStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for Entrance screen
 */
class EntranceViewModel(private val useCase: GetUserPreferencesEntranceUseCase) : ViewModel() {

    private val _states = MutableStateFlow(EntranceStates())
    val states: SharedFlow<EntranceStates> = _states.asStateFlow()

    fun handleIntent(intent: EntranceIntent) = viewModelScope.launch {
        when (intent) {
            is EntranceIntent.OnGetStartedClicked -> onGetStartedClicked()
        }
    }

    fun onGetStartedClicked() = viewModelScope.launch {
        val nextDestination = when {
            !useCase.isLanguageSelected() -> EntranceDestination.Language
            !useCase.isOnBoardingCompleted() -> EntranceDestination.OnBoarding
            else -> EntranceDestination.Home
        }

        _states.update { it.copy(nextDestination) }
    }
}