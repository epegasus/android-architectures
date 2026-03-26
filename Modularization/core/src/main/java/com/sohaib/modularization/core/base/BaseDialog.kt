package com.sohaib.modularization.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

/**
 * Base Dialog with modern Android features
 * Uses ViewBinding for UI binding
 */
abstract class BaseDialog<VB : ViewBinding, VM : ViewModel> : DialogFragment() {
    
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM
    
    @get:LayoutRes
    protected abstract val layoutRes: Int
    
    protected abstract val viewModelClass: KClass<VM>
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Override in subclasses to setup ViewBinding
        return null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Setup view model
        setupViewModel()
        
        // Setup UI
        setupUI()
        
        // Setup observers
        setupObservers()
    }
    
    private fun setupViewModel() {
        viewModel = getViewModel(viewModelClass)
    }
    
    protected open fun setupUI() {
        // Override in subclasses for UI setup
    }
    
    protected open fun setupObservers() {
        // Override in subclasses for observers
    }
    
    /**
     * Show dialog with proper lifecycle management
     */
    fun showDialog() {
        if (!isAdded && !isRemoving) {
            show(parentFragmentManager, tag)
        }
    }
    
    /**
     * Dismiss dialog with proper cleanup
     */
    fun dismissDialog() {
        if (isAdded) {
            dismiss()
        }
    }
    
    /**
     * Get ViewModel instance
     */
    private fun <T : ViewModel> getViewModel(modelClass: KClass<T>): T {
        return ViewModelProvider(this)[modelClass.java]
    }
}
