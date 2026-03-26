package com.sohaib.nextgensdk.demo.utils.base.activity

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.sohaib.nextgensdk.demo.di.DIComponent

abstract class BaseActivity<T : ViewBinding>(bindingFactory: (LayoutInflater) -> T) : ParentActivity<T>(bindingFactory) {
    protected val diComponent by lazy { DIComponent() }
}