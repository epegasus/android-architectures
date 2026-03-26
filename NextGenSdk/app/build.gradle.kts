plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.sohaib.nextgensdk.demo"

    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.sohaib.nextgensdk.demo"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            resValue("string", "admob_app_id", "ca-app-pub-3940256099942544~3347511713")

            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        release {
            resValue("string", "admob_app_id", "ca-app-pub-3940256099942544~3347511713")

            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        resValues = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":sdkAdsNextGen"))
    implementation(project(":core"))

    // Android Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Fragment
    implementation(libs.androidx.fragment)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Navigational Components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // GMS (NextGen)
    implementation(libs.ads.mobile.sdk)
    //implementation(libs.play.services.ads)

    // Koin
    implementation(libs.koin.android)
}