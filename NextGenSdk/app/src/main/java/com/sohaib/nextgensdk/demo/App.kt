package com.sohaib.nextgensdk.demo

import android.app.Application
import com.sohaib.nextgensdk.demo.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }
}