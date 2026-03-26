package com.sohaib.modularization.domain.settings.entity

/**
 * Domain entity representing app settings
 */
data class Settings(
    val theme: String = "SYSTEM", // "LIGHT", "DARK", "SYSTEM"
    val language: String = "en",
    val notifications: Boolean = true,
    val autoPlay: Boolean = false,
    val imageQuality: String = "HIGH", // "LOW", "MEDIUM", "HIGH", "ORIGINAL"
    val videoQuality: String = "HIGH", // "LOW", "MEDIUM", "HIGH", "ORIGINAL"
    val cacheSize: Long = 0L,
    val lastBackup: Long = 0L,
    val itemClick: () -> Unit = {},
    val settingClick: () -> Unit = {}
)