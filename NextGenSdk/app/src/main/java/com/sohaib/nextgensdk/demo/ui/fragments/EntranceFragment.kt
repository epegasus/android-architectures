package com.sohaib.nextgensdk.demo.ui.fragments

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.sohaib.admobpreloader.bannerAds.enums.BannerAdKey
import com.sohaib.admobpreloader.interstitialAds.callbacks.InterstitialLoadListener
import com.sohaib.admobpreloader.interstitialAds.callbacks.InterstitialShowListener
import com.sohaib.admobpreloader.interstitialAds.enums.InterAdKey
import com.sohaib.admobpreloader.nativeAds.enums.NativeAdKey
import com.sohaib.admobpreloader.utils.addCleanView
import com.sohaib.nextgensdk.demo.R
import com.sohaib.nextgensdk.demo.databinding.FragmentEntranceBinding
import com.sohaib.nextgensdk.demo.utils.base.fragment.BaseFragment
import com.sohaib.nextgensdk.demo.utils.constants.Constants

class EntranceFragment : BaseFragment<FragmentEntranceBinding>(FragmentEntranceBinding::inflate) {

    override fun onViewCreated() {
        startTimeout()
        loadAd()

        binding.mbShowAd.setOnClickListener { showAd() }
    }

    private fun startTimeout() {
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d(Constants.TAG, "startTimeout: Starting timeout")
            onAdResponse("Ad Timeout")
        }, 2000)
    }

    private fun loadAd() {
        diComponent.interstitialAdsManager.loadInterstitialAd(InterAdKey.ENTRANCE, object : InterstitialLoadListener {
            override fun onLoaded(key: String) = onAdResponse("Ad loaded: $key")
            override fun onFailed(key: String, message: String) = onAdResponse("Ad failed to load: ${message.take(50)}")
        })

        // Preload native for Language screen ahead of time
        diComponent.nativeAdsManager.loadNativeAd(NativeAdKey.LANGUAGE)

        // Preload banner for Entrance bottom placement with adaptive size
        diComponent.bannerAdsManager.loadBannerAd(BannerAdKey.ENTRANCE) { showBanner() }
    }

    private fun onAdResponse(message: String) {
        if (isAdded.not()) return
        binding.cpiProgress.visibility = View.GONE
        binding.mbShowAd.isEnabled = true
        binding.mtvTitle.text = message
    }

    fun showAd() {
        diComponent.interstitialAdsManager.showInterstitialAd(activity, InterAdKey.ENTRANCE, object : InterstitialShowListener {
            override fun onAdFailedToShow(key: String, reason: String) = navigateScreen()
            override fun onAdImpressionDelayed(key: String) = navigateScreen()
        })
    }

    private fun showBanner() {
        val act = activity ?: return
        if (isAdded.not()) return
        diComponent.bannerAdsManager.pollBannerAd(key = BannerAdKey.ENTRANCE)?.let {
            binding.flBanner.addCleanView(it.getView(act))
        }
    }

    private fun navigateScreen() {
        findNavController().navigate(R.id.action_entranceFragment_to_languageFragment)
    }
}