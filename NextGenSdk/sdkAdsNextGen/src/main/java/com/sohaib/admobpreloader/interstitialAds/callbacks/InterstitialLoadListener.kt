package com.sohaib.admobpreloader.interstitialAds.callbacks

interface InterstitialLoadListener {
    fun onLoaded(key: String) {}
    fun onFailed(key: String, message: String) {}
}
