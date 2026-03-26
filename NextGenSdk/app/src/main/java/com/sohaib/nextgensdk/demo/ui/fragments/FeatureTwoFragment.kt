package com.sohaib.nextgensdk.demo.ui.fragments

import androidx.navigation.fragment.findNavController
import com.sohaib.admobpreloader.bannerAds.enums.BannerAdKey
import com.sohaib.admobpreloader.interstitialAds.callbacks.InterstitialShowListener
import com.sohaib.admobpreloader.interstitialAds.enums.InterAdKey
import com.sohaib.admobpreloader.utils.addCleanView
import com.sohaib.nextgensdk.demo.databinding.FragmentFeatureTwoBinding
import com.sohaib.nextgensdk.demo.utils.base.fragment.BaseFragment

class FeatureTwoFragment : BaseFragment<FragmentFeatureTwoBinding>(FragmentFeatureTwoBinding::inflate) {

    override fun onViewCreated() {
        loadAd()
        loadBanners()

        binding.mbBack.setOnClickListener { checkInterstitialAd() }
    }

    private fun loadAd() {
        diComponent.interstitialAdsManager.loadInterstitialAd(InterAdKey.BACK_PRESS)
    }

    private fun loadBanners() {
        // Top collapsible banner (FEATURE_TWO_A)
        diComponent.bannerAdsManager.loadBannerAd(BannerAdKey.FEATURE_TWO_A) {
            showTopBanner()
        }

        // Bottom collapsible banner (FEATURE_TWO_B)
        diComponent.bannerAdsManager.loadBannerAd(BannerAdKey.FEATURE_TWO_B) {
            showBottomBanner()
        }
    }

    private fun checkInterstitialAd() {
        diComponent.interstitialAdsManager.showInterstitialAd(activity, InterAdKey.BACK_PRESS, object : InterstitialShowListener {
            override fun onAdFailedToShow(key: String, reason: String) = navigateBack()
            override fun onAdImpressionDelayed(key: String) = navigateBack()
        })
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    private fun showTopBanner() {
        if (isAdded.not()) return
        val act = activity ?: return
        diComponent.bannerAdsManager.pollBannerAd(BannerAdKey.FEATURE_TWO_A)?.let {
            binding.flBannerTop.addCleanView(it.getView(act))
        }
    }

    private fun showBottomBanner() {
        if (isAdded.not()) return
        val act = activity ?: return
        diComponent.bannerAdsManager.pollBannerAd(BannerAdKey.FEATURE_TWO_B)?.let {
            binding.flBannerBottom.addCleanView(it.getView(act))
        }
    }
}