package com.sohaib.modularization.feature.entrance.ui

import com.sohaib.modularization.core.base.BaseFragment
import com.sohaib.modularization.core.flows.repeatWhenStarted
import com.sohaib.modularization.feature.entrance.databinding.FragmentEntranceBinding
import com.sohaib.modularization.feature.entrance.intent.EntranceIntent
import com.sohaib.modularization.feature.entrance.states.EntranceDestination
import com.sohaib.modularization.feature.entrance.viewModel.EntranceViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Entrance screen fragment
 */
class EntranceFragment : BaseFragment<FragmentEntranceBinding>(FragmentEntranceBinding::inflate) {

    private val viewModel by viewModel<EntranceViewModel>()

    override fun onViewCreated() {
        binding.mbGetStarted.setOnClickListener { viewModel.handleIntent(EntranceIntent.OnGetStartedClicked) }
    }

    override fun initObservers() {
        repeatWhenStarted {
            viewModel.states.collect { state ->
                when (state.navigateTo) {
                    is EntranceDestination.Language -> appNavigator.openLanguageFromEntrance()
                    is EntranceDestination.OnBoarding -> appNavigator.openOnBoardingFromEntrance()
                    is EntranceDestination.Home -> appNavigator.openDashboardFromEntrance()
                    else -> {}
                }
            }
        }
    }
}