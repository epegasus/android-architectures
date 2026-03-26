package com.sohaib.modularization.di

import androidx.appcompat.app.AppCompatActivity
import com.sohaib.modularization.core.navigation.AppNavigator
import com.sohaib.modularization.navigation.AppNavigatorImpl
import org.koin.dsl.module

val navigationModule = module {
    factory<AppNavigator> { (activity: AppCompatActivity) -> AppNavigatorImpl(activity) }
}