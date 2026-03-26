package com.sohaib.admobpreloader.interstitialAds.di

import com.sohaib.admobpreloader.interstitialAds.InterstitialAdsManager
import com.sohaib.admobpreloader.interstitialAds.engine.PreloadEngine
import com.sohaib.admobpreloader.interstitialAds.engine.ShowEngine
import com.sohaib.admobpreloader.interstitialAds.storage.AdRegistry
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Koin module for InterstitialAdsManager and its dependencies.
 */
val interstitialAdsModule = module {
    single { AdRegistry() }
    single { PreloadEngine(get()) }
    single { ShowEngine(get(), get()) }
    single {
        InterstitialAdsManager(
            resources = androidContext().resources,
            registry = get(),
            preloadEngine = get(),
            showEngine = get(),
            internetManager = get(),
            sharedPrefs = get(),
        )
    }
}