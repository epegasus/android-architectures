package com.sohaib.modularization.core.base

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.sohaib.modularization.core.navigation.AppNavigator
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf

abstract class BaseActivity<T : ViewBinding>(bindingFactory: (LayoutInflater) -> T) : ParentActivity<T>(bindingFactory) {

    protected val appNavigator: AppNavigator by lazy {
        getKoin().get<AppNavigator> { parametersOf(this) }
    }
}