package com.sohaib.nextgensdk.demo.di

import com.sohaib.admobpreloader.interstitialAds.di.interstitialAdsModule
import com.sohaib.admobpreloader.nativeAds.di.nativeAdsModule
import com.sohaib.admobpreloader.bannerAds.di.bannerAdsModule
import com.sohaib.core.di.coreModules

val appModules = listOf(
    coreModules,
    interstitialAdsModule,
    nativeAdsModule,
    bannerAdsModule
)