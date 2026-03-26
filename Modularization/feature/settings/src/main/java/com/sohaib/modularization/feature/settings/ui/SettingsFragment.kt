package com.sohaib.modularization.feature.settings.ui

import androidx.core.view.isVisible
import com.sohaib.modularization.core.base.BaseFragment
import com.sohaib.modularization.core.extensions.showToast
import com.sohaib.modularization.core.flows.repeatWhenStarted
import com.sohaib.modularization.feature.settings.adapter.SettingsAdapter
import com.sohaib.modularization.feature.settings.databinding.FragmentSettingsBinding
import com.sohaib.modularization.feature.settings.intent.SettingsIntent
import com.sohaib.modularization.feature.settings.states.SettingsDestination
import com.sohaib.modularization.feature.settings.states.SettingsState
import com.sohaib.modularization.feature.settings.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Settings screen fragment
 */
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel by viewModel<SettingsViewModel>()

    private val adapter by lazy { SettingsAdapter() }

    override fun onViewCreated() {
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        // Refresh settings when fragment becomes visible
        viewModel.handleIntent(SettingsIntent.RefreshSettings)
    }

    private fun setupRecyclerView() {
        binding.rcvList.adapter = adapter
    }

    override fun initObservers() {
        repeatWhenStarted {
            viewModel.states.collect { state ->
                render(state)
            }
        }
    }

    private fun render(state: SettingsState) {
        binding.cpiProgress.isVisible = state.isLoading

        adapter.submitList(createSettingsItems(state.settings))

        state.errorMessageRes?.let {
            context.showToast(it)
            viewModel.handleIntent(SettingsIntent.ErrorCleared)
        }

        state.navigateTo?.let {
            when (it) {
                is SettingsDestination.ThemeChanged -> context.showToast("Theme updated")
                is SettingsDestination.LanguageChanged -> context.showToast("Language updated")
                is SettingsDestination.CacheCleared -> context.showToast("Cache cleared")
                is SettingsDestination.SettingsReset -> context.showToast("Settings reset to default")
            }
        }
    }

    private fun createSettingsItems(settings: com.sohaib.modularization.domain.settings.entity.Settings): List<SettingsItem> {
        return listOf(
            SettingsItem.Header("Appearance"),
            SettingsItem.Switch(
                "Dark Theme",
                "Enable dark theme",
                settings.theme == "DARK"
            ),
            SettingsItem.SingleChoice(
                "Language",
                "Select app language",
                settings.language,
                listOf("en", "es", "fr", "de", "it", "pt", "ru", "ja", "ko", "zh", "ar", "hi")
            ),
            SettingsItem.Header("Media"),
            SettingsItem.Switch(
                "Auto Play Videos",
                "Automatically play videos",
                settings.autoPlay
            ),
            SettingsItem.SingleChoice(
                "Image Quality",
                "Quality for image display",
                settings.imageQuality,
                listOf("LOW", "MEDIUM", "HIGH", "ORIGINAL")
            ),
            SettingsItem.SingleChoice(
                "Video Quality",
                "Quality for video playback",
                settings.videoQuality,
                listOf("LOW", "MEDIUM", "HIGH", "ORIGINAL")
            ),
            SettingsItem.Header("Notifications"),
            SettingsItem.Switch(
                "Enable Notifications",
                "Receive app notifications",
                settings.notifications
            ),
            SettingsItem.Header("Storage"),
            SettingsItem.Info(
                "Cache Size",
                "Current cache size",
                "${settings.cacheSize / (1024 * 1024)} MB"
            ),
            SettingsItem.Info(
                "Last Backup",
                "Last backup date",
                if (settings.lastBackup > 0) {
                    java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault())
                        .format(java.util.Date(settings.lastBackup))
                } else {
                    "Never"
                }
            ),
            SettingsItem.Action(
                "Clear Cache",
                "Clear all cached data",
                "Clear"
            ),
            SettingsItem.Action(
                "Reset Settings",
                "Reset all settings to default",
                "Reset"
            )
        )
    }
}