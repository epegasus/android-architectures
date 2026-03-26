package com.sohaib.nextgensdk.demo.di

import com.sohaib.admobpreloader.interstitialAds.InterstitialAdsManager
import com.sohaib.admobpreloader.nativeAds.NativeAdsManager
import com.sohaib.admobpreloader.bannerAds.BannerAdsManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DIComponent : KoinComponent {

    val interstitialAdsManager by inject<InterstitialAdsManager>()
    val nativeAdsManager by inject<NativeAdsManager>()
    val bannerAdsManager by inject<BannerAdsManager>()

}