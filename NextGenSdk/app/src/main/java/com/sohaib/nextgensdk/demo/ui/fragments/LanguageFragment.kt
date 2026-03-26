package com.sohaib.nextgensdk.demo.ui.fragments

import androidx.navigation.fragment.findNavController
import com.sohaib.admobpreloader.interstitialAds.callbacks.InterstitialShowListener
import com.sohaib.admobpreloader.interstitialAds.enums.InterAdKey
import com.sohaib.admobpreloader.nativeAds.enums.NativeAdKey
import com.sohaib.nextgensdk.demo.R
import com.sohaib.nextgensdk.demo.databinding.FragmentLanguageBinding
import com.sohaib.nextgensdk.demo.utils.base.fragment.BaseFragment

class LanguageFragment : BaseFragment<FragmentLanguageBinding>(FragmentLanguageBinding::inflate) {

    override fun onViewCreated() {
        loadAds()

        binding.mbContinue.setOnClickListener { checkInterstitialAd() }
    }

    private fun loadAds() {
        loadNative()
    }

    private fun loadNative() {
        diComponent.nativeAdsManager.loadNativeAd(NativeAdKey.LANGUAGE) { showNativeAd() }
    }

    private fun showNativeAd() {
        if (isAdded.not()) return
        diComponent.nativeAdsManager.pollNativeAd(key = NativeAdKey.LANGUAGE, showCallback = null)?.let {
            binding.nativeAdView.setNativeAd(it)
        }
    }

    private fun checkInterstitialAd() {
        diComponent.interstitialAdsManager.showInterstitialAd(activity, InterAdKey.ENTRANCE, object : InterstitialShowListener {
            override fun onAdFailedToShow(key: String, reason: String) = navigateScreen()
            override fun onAdImpressionDelayed(key: String) = navigateScreen()
        })
    }

    private fun navigateScreen() {
        findNavController().navigate(R.id.action_languageFragment_to_onBoardingFragment)
    }
}