package com.sohaib.modularization

import android.app.Application
import com.sohaib.modularization.core.di.coreModule
import com.sohaib.modularization.data.entrance.di.entranceDataModule
import com.sohaib.modularization.data.favourites.di.favouritesDataModule
import com.sohaib.modularization.data.images.di.imagesDataModule
import com.sohaib.modularization.data.language.di.languageDataModule
import com.sohaib.modularization.data.media.detail.di.mediaDetailDataModule
import com.sohaib.modularization.data.onboarding.di.onboardingDataModule
import com.sohaib.modularization.data.settings.di.settingsDataModule
import com.sohaib.modularization.data.videos.di.videosDataModule
import com.sohaib.modularization.di.navigationModule
import com.sohaib.modularization.domain.entrance.di.entranceDomainModule
import com.sohaib.modularization.domain.favourites.di.favouritesDomainModule
import com.sohaib.modularization.domain.images.di.imagesDomainModule
import com.sohaib.modularization.domain.language.di.languageDomainModule
import com.sohaib.modularization.domain.media.detail.di.mediaDetailDomainModule
import com.sohaib.modularization.domain.onboarding.di.onboardingDomainModule
import com.sohaib.modularization.domain.settings.di.settingsDomainModule
import com.sohaib.modularization.domain.videos.di.videosDomainModule
import com.sohaib.modularization.feature.dashboard.di.dashboardFeatureModule
import com.sohaib.modularization.feature.entrance.di.entranceFeatureModule
import com.sohaib.modularization.feature.favourites.di.favouritesFeatureModule
import com.sohaib.modularization.feature.images.di.imagesFeatureModule
import com.sohaib.modularization.feature.language.di.languageFeatureModule
import com.sohaib.modularization.feature.media.detail.di.mediaDetailFeatureModule
import com.sohaib.modularization.feature.onboarding.di.onboardingFeatureModule
import com.sohaib.modularization.feature.settings.di.settingsFeatureModule
import com.sohaib.modularization.feature.videos.di.videosFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class with Koin initialization
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Koin
        startKoin {
            androidContext(this@App)
            modules(
                // Core modules
                coreModule,
                navigationModule,

                // Data modules
                entranceDataModule,
                languageDataModule,
                onboardingDataModule,
                imagesDataModule,
                videosDataModule,
                favouritesDataModule,
                settingsDataModule,
                mediaDetailDataModule,

                // Domain modules
                entranceDomainModule,
                languageDomainModule,
                onboardingDomainModule,
                imagesDomainModule,
                videosDomainModule,
                favouritesDomainModule,
                settingsDomainModule,
                mediaDetailDomainModule,

                // Feature modules
                entranceFeatureModule,
                languageFeatureModule,
                onboardingFeatureModule,
                dashboardFeatureModule,
                imagesFeatureModule,
                videosFeatureModule,
                favouritesFeatureModule,
                settingsFeatureModule,
                mediaDetailFeatureModule
            )
        }
    }
}
