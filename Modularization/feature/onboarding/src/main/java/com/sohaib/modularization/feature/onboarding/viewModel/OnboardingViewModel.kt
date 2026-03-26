package com.sohaib.modularization.feature.onboarding.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohaib.modularization.domain.onboarding.usecase.CompleteOnboardingStepUseCase
import com.sohaib.modularization.domain.onboarding.usecase.GetOnboardingStepsUseCase
import com.sohaib.modularization.feature.onboarding.intent.OnBoardingIntent
import com.sohaib.modularization.feature.onboarding.states.OnBoardingDestination
import com.sohaib.modularization.feature.onboarding.states.OnBoardingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for Onboarding screen
 */
class OnboardingViewModel(
    private val getOnboardingStepsUseCase: GetOnboardingStepsUseCase,
    private val completeOnboardingStepUseCase: CompleteOnboardingStepUseCase
) : ViewModel() {

    private val _states = MutableStateFlow(OnBoardingState())
    val states: StateFlow<OnBoardingState> = _states.asStateFlow()

    init {
        handleIntent(OnBoardingIntent.LoadTutorials)
    }

    fun handleIntent(intent: OnBoardingIntent) = viewModelScope.launch {
        when (intent) {
            is OnBoardingIntent.LoadTutorials -> loadSteps()
            is OnBoardingIntent.PageChanged -> setCurrentPage(intent.position)
            is OnBoardingIntent.NextClicked -> goToNext()
            is OnBoardingIntent.SkipClicked -> completeOnboarding()
            is OnBoardingIntent.CompleteClicked -> completeOnboarding()
            is OnBoardingIntent.NavigationCleared -> clearNavigation()
        }
    }

    private fun loadSteps() = viewModelScope.launch {
        _states.update { it.copy(isLoading = true) }
        getOnboardingStepsUseCase().collect { steps ->
            _states.update {
                it.copy(
                    isLoading = false,
                    steps = steps,
                    progressPercent = calculateProgress(0, steps.size)
                )
            }
        }
    }

    private fun setCurrentPage(position: Int) {
        val stepsCount = _states.value.steps.size
        val newStep = position.coerceIn(0, stepsCount - 1)
        _states.update {
            it.copy(
                currentStep = newStep,
                progressPercent = calculateProgress(newStep, stepsCount)
            )
        }
    }

    private fun goToNext() {
        val current = _states.value.currentStep
        val stepsCount = _states.value.steps.size

        if (current < stepsCount - 1) {
            setCurrentPage(current + 1)
        } else {
            completeOnboarding()
        }
    }

    private fun completeOnboarding() = viewModelScope.launch {
        completeOnboardingStepUseCase()
        _states.update { it.copy(navigateTo = OnBoardingDestination.Dashboard) }
    }

    private fun clearNavigation() {
        _states.update { it.copy(navigateTo = null) }
    }

    private fun calculateProgress(current: Int, total: Int): Int {
        if (total == 0) return 0
        return ((current + 1) * 100) / total
    }
}