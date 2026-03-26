package com.sohaib.modularization.core.mediastore

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Enum representing permission status
 */
enum class PermissionStatus {
    GRANTED,
    DENIED,
    DENIED_PERMANENTLY,
    UNKNOWN
}

/**
 * Manager for handling media permissions with Android 6-16 support
 */
class PermissionManager(private val context: Context) {

    private val _permissionStatus = MutableStateFlow(PermissionStatus.UNKNOWN)
    val permissionStatus: StateFlow<PermissionStatus> = _permissionStatus.asStateFlow()

    /**
     * Check current permission status
     */
    fun checkPermissionStatus(): PermissionStatus {
        return when {
            hasFullMediaPermissions() -> PermissionStatus.GRANTED
            hasLimitedMediaPermissions() -> PermissionStatus.DENIED // Limited access is still a form of denial
            else -> PermissionStatus.DENIED
        }
    }

    /**
     * Check if full media permissions are granted
     */
    private fun hasFullMediaPermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ (API 33+)
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_MEDIA_VIDEO
                    ) == PackageManager.PERMISSION_GRANTED
        } else {
            // Android 12 and below
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * Check if limited media permissions are granted (Android 14+)
     */
    private fun hasLimitedMediaPermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            // Android 14+ (API 34+)
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
    }

    /**
     * Get required permissions for current Android version
     */
    fun getRequiredPermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ (API 33+)
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO
            )
        } else {
            // Android 12 and below
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    /**
     * Check if permission rationale should be shown
     */
    fun shouldShowRationale(): Boolean {
        // This should be called from Activity/Fragment
        return false
    }

    /**
     * Navigate to app settings
     */
    fun navigateToSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent)
    }

    /**
     * Update permission status
     */
    fun updatePermissionStatus() {
        _permissionStatus.value = checkPermissionStatus()
    }

    /**
     * Get permission status message
     */
    fun getPermissionStatusMessage(): String {
        return when (checkPermissionStatus()) {
            PermissionStatus.GRANTED -> "Full media access granted"
            PermissionStatus.DENIED -> "Media access denied"
            PermissionStatus.DENIED_PERMANENTLY -> "Media access permanently denied"
            PermissionStatus.UNKNOWN -> "Permission status unknown"
        }
    }
}