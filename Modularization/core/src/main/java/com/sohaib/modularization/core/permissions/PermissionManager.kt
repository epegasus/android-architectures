package com.sohaib.modularization.core.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Permission manager for handling media permissions with Android 14+ support
 */
class PermissionManager {
    
    private val _permissionEvents = MutableSharedFlow<PermissionEvent>()
    val permissionEvents: SharedFlow<PermissionEvent> = _permissionEvents.asSharedFlow()
    
    companion object {
        const val PERMISSION_REQUEST_CODE = 1001
        const val SETTINGS_REQUEST_CODE = 1002
        
        // Media permissions for different Android versions
        val MEDIA_PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
            )
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
    
    /**
     * Check if all required permissions are granted
     */
    fun arePermissionsGranted(context: Context): Boolean {
        return MEDIA_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }
    }
    
    /**
     * Check if limited access is granted (Android 14+)
     */
    fun isLimitedAccessGranted(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
    }
    
    /**
     * Request permissions from Activity
     */
    fun requestPermissions(activity: Activity) {
        val permissionsToRequest = MEDIA_PERMISSIONS.filter { permission ->
            ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED
        }
        
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }
    }
    
    /**
     * Request permissions from Fragment
     */
    fun requestPermissions(fragment: Fragment) {
        val permissionsToRequest = MEDIA_PERMISSIONS.filter { permission ->
            ContextCompat.checkSelfPermission(fragment.requireContext(), permission) != PackageManager.PERMISSION_GRANTED
        }
        
        if (permissionsToRequest.isNotEmpty()) {
            fragment.requestPermissions(
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }
    }
    
    /**
     * Handle permission result
     */
    fun handlePermissionResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val allGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            val someGranted = grantResults.any { it == PackageManager.PERMISSION_GRANTED }
            
            when {
                allGranted -> {
                    // All permissions granted
                }
                someGranted -> {
                    // Some permissions granted, check for limited access
                    if (isLimitedAccessGranted(permissions, grantResults)) {
                        // Limited access granted
                    } else {
                        // Partial permissions, show warning
                    }
                }
                else -> {
                    // No permissions granted
                }
            }
        }
    }
    
    /**
     * Check if limited access is granted from permission results
     */
    private fun isLimitedAccessGranted(permissions: Array<String>, grantResults: IntArray): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val limitedAccessIndex = permissions.indexOf(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)
            return limitedAccessIndex != -1 && grantResults[limitedAccessIndex] == PackageManager.PERMISSION_GRANTED
        }
        return false
    }
    
    /**
     * Open app settings
     */
    fun openAppSettings(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent)
    }
    
    /**
     * Show permission rationale
     */
    fun shouldShowRationale(activity: Activity): Boolean {
        return MEDIA_PERMISSIONS.any { permission ->
            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
        }
    }
    
    /**
     * Show permission rationale for Fragment
     */
    fun shouldShowRationale(fragment: Fragment): Boolean {
        return MEDIA_PERMISSIONS.any { permission ->
            fragment.shouldShowRequestPermissionRationale(permission)
        }
    }
}

/**
 * Permission events
 */
sealed class PermissionEvent {
    object AllPermissionsGranted : PermissionEvent()
    object LimitedAccessGranted : PermissionEvent()
    object PartialPermissionsGranted : PermissionEvent()
    object NoPermissionsGranted : PermissionEvent()
    object ShowPermissionRationale : PermissionEvent()
    object OpenAppSettings : PermissionEvent()
}
