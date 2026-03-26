package com.sohaib.modularization.feature.dashboard.ui

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.sohaib.modularization.core.base.BaseFragment
import com.sohaib.modularization.feature.dashboard.databinding.FragmentDashboardBinding
import com.sohaib.modularization.feature.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Dashboard screen fragment with bottom navigation
 */
class DashboardFragment : BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private val viewModel by viewModel<DashboardViewModel>()

    override fun onViewCreated() {
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment = childFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnItemSelectedListener { it.onNavDestinationSelected(navController) }
    }
}