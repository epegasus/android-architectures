package com.sohaib.modularization.core.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.viewbinding.ViewBinding
import com.google.android.material.color.DynamicColors
import com.sohaib.modularization.core.constants.Constants.TAG

abstract class ParentActivity<T : ViewBinding>(private val bindingFactory: (LayoutInflater) -> T) : AppCompatActivity() {

    protected val binding by lazy { bindingFactory(layoutInflater) }
    protected var includeTopPadding = true
    protected var includeBottomPadding = true
    protected var enableKeyboardInsets = false
    var statusBarHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        onPreCreated()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setPadding()
        onCreated()
        initObservers()
    }

    open fun onPreCreated() {}
    open fun initObservers() {}

    private fun setPadding() {
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            when (enableKeyboardInsets) {
                true -> setPaddingKeyboard(v, insets)
                false -> setPaddingNormal(v, insets)
            }
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun setPaddingNormal(v: View, insets: WindowInsetsCompat) {
        val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())

        statusBarHeight = bars.top

        val topPadding = if (includeTopPadding) bars.top else 0
        val bottomPadding = if (includeBottomPadding) bars.bottom else 0

        v.updatePadding(
            left = bars.left,
            top = topPadding,
            right = bars.right,
            bottom = bottomPadding
        )
    }

    private fun setPaddingKeyboard(v: View, insets: WindowInsetsCompat) {
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
        val cutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout())

        if (systemBars.top != 0) {
            statusBarHeight = systemBars.top
        }

        // Use the maximum of nav bar or IME for bottom padding
        val bottomPadding = if (includeBottomPadding) maxOf(systemBars.bottom, imeInsets.bottom) else 0
        val topPadding = if (includeTopPadding) maxOf(systemBars.top, cutout.top, statusBarHeight) else 0

        v.updatePadding(
            left = systemBars.left,
            top = topPadding,
            right = systemBars.right,
            bottom = bottomPadding
        )
    }

    protected open fun installSplashTheme() {
        Log.d(TAG, "installSplashTheme: installed")
        installSplashScreen()
    }

    protected open fun enableMaterialDynamicTheme() {
        Log.d(TAG, "enableMaterialDynamicTheme: enabling")
        DynamicColors.applyToActivityIfAvailable(this)
    }

    /**
     * @param type
     *     0: Show SystemBars
     *     1: Hide StatusBars
     *     2: Hide NavigationBars
     *     3: Hide SystemBars
     */

    protected open fun hideStatusBar(type: Int) {
        Log.d(TAG, "hideStatusBar: Showing/Hiding: Type: $type")
        WindowInsetsControllerCompat(window, window.decorView).apply {
            systemBarsBehavior = when (type) {
                0 -> WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                else -> WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            when (type) {
                0 -> show(WindowInsetsCompat.Type.systemBars())
                1 -> hide(WindowInsetsCompat.Type.systemBars())
                2 -> hide(WindowInsetsCompat.Type.statusBars())
                3 -> hide(WindowInsetsCompat.Type.navigationBars())
                else -> hide(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    abstract fun onCreated()
}