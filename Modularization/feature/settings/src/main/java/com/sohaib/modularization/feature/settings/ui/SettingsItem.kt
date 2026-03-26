package com.sohaib.modularization.feature.settings.ui

/**
 * Settings item data class
 */
sealed class SettingsItem {

    data class Header(
        val title: String
    ) : SettingsItem()

    data class Switch(
        val title: String,
        val description: String,
        val isChecked: Boolean
    ) : SettingsItem()

    data class SingleChoice(
        val title: String,
        val description: String,
        val selectedValue: String,
        val options: List<String>
    ) : SettingsItem()

    data class Slider(
        val title: String,
        val description: String,
        val value: Int,
        val min: Int,
        val max: Int
    ) : SettingsItem()

    data class Info(
        val title: String,
        val description: String,
        val value: String
    ) : SettingsItem()

    data class Action(
        val title: String,
        val description: String,
        val actionText: String
    ) : SettingsItem()
}