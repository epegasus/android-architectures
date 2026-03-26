package com.sohaib.modularization.feature.onboarding.ui

import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.sohaib.modularization.core.base.BaseFragment
import com.sohaib.modularization.core.flows.repeatWhenStarted
import com.sohaib.modularization.feature.onboarding.adapter.OnboardingAdapter
import com.sohaib.modularization.feature.onboarding.databinding.FragmentOnboardingBinding
import com.sohaib.modularization.feature.onboarding.intent.OnBoardingIntent
import com.sohaib.modularization.feature.onboarding.states.OnBoardingDestination
import com.sohaib.modularization.feature.onboarding.states.OnBoardingState
import com.sohaib.modularization.feature.onboarding.viewModel.OnboardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Onboarding screen fragment
 */
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>(FragmentOnboardingBinding::inflate) {

    private val viewModel by viewModel<OnboardingViewModel>()
    private val adapter by lazy { OnboardingAdapter() }

    override fun onViewCreated() {
        setupViewPager()

        binding.mbNext.setOnClickListener { viewModel.handleIntent(OnBoardingIntent.NextClicked) }
        binding.mbSkip.setOnClickListener { viewModel.handleIntent(OnBoardingIntent.SkipClicked) }
        binding.mbGetStarted.setOnClickListener { viewModel.handleIntent(OnBoardingIntent.CompleteClicked) }
    }

    private fun setupViewPager() {
        binding.viewPager.apply {
            this.adapter = this@OnboardingFragment.adapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    viewModel.handleIntent(OnBoardingIntent.PageChanged(position))
                }
            })
        }
    }

    override fun initObservers() {
        repeatWhenStarted {
            viewModel.states.collect { state ->
                render(state)
            }
        }
    }

    private fun render(state: OnBoardingState) = with(binding) {
        cpiLoading.isVisible = state.isLoading
        adapter.submitList(state.steps)
        updateUI(state.currentStep, state.steps.size, state.progressPercent)

        state.navigateTo?.let {
            if (it is OnBoardingDestination.Dashboard) {
                appNavigator.openDashboardFromOnboarding()
            }
            viewModel.handleIntent(OnBoardingIntent.NavigationCleared)
        }
    }

    private fun updateUI(currentStep: Int, totalSteps: Int, progressPercent: Int) = with(binding) {
        // 🔹 Sync ViewPager position (only if needed)
        if (viewPager.currentItem != currentStep) {
            viewPager.setCurrentItem(currentStep, true)
        }

        // 🔹 Progress indicator
        linearProgressIndicator.progress = progressPercent

        // 🔹 Button visibility
        val isLastStep = currentStep == totalSteps - 1

        mbNext.isVisible = !isLastStep
        mbSkip.isVisible = !isLastStep
        mbGetStarted.isVisible = isLastStep
    }
}