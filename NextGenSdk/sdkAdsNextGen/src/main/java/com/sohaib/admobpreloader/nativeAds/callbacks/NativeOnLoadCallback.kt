package com.sohaib.admobpreloader.nativeAds.callbacks

fun interface NativeOnLoadCallback {
    fun onResponse(isLoaded: Boolean)
}