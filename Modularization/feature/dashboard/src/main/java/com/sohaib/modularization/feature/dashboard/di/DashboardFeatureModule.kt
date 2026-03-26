package com.sohaib.modularization.feature.dashboard.di

import com.sohaib.modularization.feature.dashboard.viewmodel.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for Dashboard feature
 */
val dashboardFeatureModule = module {
    viewModel { DashboardViewModel() }
}