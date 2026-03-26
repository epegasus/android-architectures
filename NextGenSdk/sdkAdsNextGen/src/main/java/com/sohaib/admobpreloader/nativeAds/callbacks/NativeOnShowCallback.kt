package com.sohaib.admobpreloader.nativeAds.callbacks

interface NativeOnShowCallback {
    fun onAdImpression() {}
    fun onAdImpressionDelayed() {}
    fun onAdClicked() {}
    fun onAdFailedToShow() {}
}