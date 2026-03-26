package com.sohaib.modularization.feature.language.ui

import androidx.core.view.isVisible
import com.sohaib.modularization.core.base.BaseFragment
import com.sohaib.modularization.core.extensions.showToast
import com.sohaib.modularization.core.flows.repeatWhenStarted
import com.sohaib.modularization.feature.language.adapter.LanguageAdapter
import com.sohaib.modularization.feature.language.databinding.FragmentLanguageBinding
import com.sohaib.modularization.feature.language.intent.LanguageIntent
import com.sohaib.modularization.feature.language.states.LanguageDestination
import com.sohaib.modularization.feature.language.states.LanguageState
import com.sohaib.modularization.feature.language.viewModel.LanguageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Language selection screen fragment
 */
class LanguageFragment : BaseFragment<FragmentLanguageBinding>(FragmentLanguageBinding::inflate) {

    private val viewModel by viewModel<LanguageViewModel>()
    private val adapter by lazy { LanguageAdapter() }

    override fun onViewCreated() {
        initRecyclerView()

        binding.mbContinue.setOnClickListener { viewModel.handleIntent(LanguageIntent.ContinueClicked) }
    }

    private fun initRecyclerView() {
        binding.rcvLanguages.adapter = adapter
    }

    override fun initObservers() {
        repeatWhenStarted {
            viewModel.states.collect { state ->
                render(state)
            }
        }
    }

    private fun render(state: LanguageState) {
        binding.cpiProgress.isVisible = state.isLoading

        adapter.submitList(state.languages)

        state.navigateTo?.let {
            when (it) {
                LanguageDestination.OnBoarding -> appNavigator.openOnBoardingFromLanguage()
            }
            // Reset navigation after handling to avoid re-trigger on rotation
            viewModel.handleIntent(LanguageIntent.NavigationCleared)
        }

        state.errorMessageRes?.let {
            context.showToast(it)
            viewModel.handleIntent(LanguageIntent.ErrorCleared)
        }
    }
}