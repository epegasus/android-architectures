package com.sohaib.admobpreloader.bannerAds.callbacks

/**
 * Simple callback for banner preload results.
 */
fun interface BannerOnLoadCallback {
    fun onResponse(isLoaded: Boolean)
}