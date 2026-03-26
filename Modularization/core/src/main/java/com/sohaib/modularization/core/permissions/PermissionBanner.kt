package com.sohaib.modularization.core.permissions

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.sohaib.modularization.core.R

/**
 * Permission banner for showing permission warnings and actions
 */
class PermissionBanner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    
    private val cardView: MaterialCardView
    private val titleText: MaterialTextView
    private val messageText: MaterialTextView
    private val allowButton: MaterialButton
    
    private var onAllowClicked: (() -> Unit)? = null
    private var onDismissClicked: (() -> Unit)? = null
    
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_permission_banner, this, true)
        
        cardView = findViewById(R.id.card_permission_banner)
        titleText = findViewById(R.id.tv_permission_title)
        messageText = findViewById(R.id.tv_permission_message)
        allowButton = findViewById(R.id.btn_permission_allow)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        allowButton.setOnClickListener {
            onAllowClicked?.invoke()
        }
    }
    
    /**
     * Show limited access warning
     */
    fun showLimitedAccessWarning() {
        titleText.text = "Limited Access"
        messageText.text = "You have granted limited access to media. For full functionality, please grant full access in settings."
        allowButton.text = "Allow Full Access"
        allowButton.setOnClickListener {
            onAllowClicked?.invoke()
        }
        visibility = VISIBLE
    }
    
    /**
     * Show no permission warning
     */
    fun showNoPermissionWarning() {
        titleText.text = "Permission Required"
        messageText.text = "This app needs access to your media files to display images and videos."
        allowButton.text = "Grant Permission"
        allowButton.setOnClickListener {
            onAllowClicked?.invoke()
        }
        visibility = VISIBLE
    }
    
    /**
     * Show partial permission warning
     */
    fun showPartialPermissionWarning() {
        titleText.text = "Partial Access"
        messageText.text = "Some permissions are missing. For the best experience, please grant all requested permissions."
        allowButton.text = "Grant All"
        allowButton.setOnClickListener {
            onAllowClicked?.invoke()
        }
        visibility = VISIBLE
    }
    
    /**
     * Hide the banner
     */
    fun hide() {
        visibility = GONE
    }
    
    /**
     * Set click listeners
     */
    fun setOnAllowClicked(listener: () -> Unit) {
        onAllowClicked = listener
    }
    
    fun setOnDismissClicked(listener: () -> Unit) {
        onDismissClicked = listener
    }
}
