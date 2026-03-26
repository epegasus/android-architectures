package com.sohaib.nextgensdk.demo.utils.base.fragment

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.sohaib.nextgensdk.demo.di.DIComponent

abstract class BaseFragment<T : ViewBinding>(bindingFactory: (LayoutInflater) -> T) : ParentFragment<T>(bindingFactory) {

    protected val diComponent by lazy { DIComponent() }
}