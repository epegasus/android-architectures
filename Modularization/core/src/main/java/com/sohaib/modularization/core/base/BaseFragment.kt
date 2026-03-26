package com.sohaib.modularization.core.base

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.sohaib.modularization.core.navigation.AppNavigator
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf

abstract class BaseFragment<T : ViewBinding>(bindingFactory: (LayoutInflater) -> T) : ParentFragment<T>(bindingFactory) {

    protected val appNavigator: AppNavigator by lazy {
        getKoin().get<AppNavigator> { parametersOf(requireActivity() as AppCompatActivity) }
    }
}