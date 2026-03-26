import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sohaib.modularization"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.sohaib.modularization"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    buildFeatures {
        viewBinding = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
            excludes += "META-INF/*.kotlin_module"
        }
    }
}

dependencies {
    // Core modules
    implementation(project(":core"))
    implementation(project(":core:database"))
    implementation(project(":core:mediastore"))
    implementation(project(":core:navigation"))
    implementation(project(":core:theme"))

    // Data modules
    implementation(project(":data:entrance"))
    implementation(project(":data:language"))
    implementation(project(":data:onboarding"))
    implementation(project(":data:images"))
    implementation(project(":data:videos"))
    implementation(project(":data:favourites"))
    implementation(project(":data:settings"))
    implementation(project(":data:media-detail"))

    // Domain modules
    implementation(project(":domain:entrance"))
    implementation(project(":domain:language"))
    implementation(project(":domain:onboarding"))
    implementation(project(":domain:images"))
    implementation(project(":domain:videos"))
    implementation(project(":domain:favourites"))
    implementation(project(":domain:settings"))
    implementation(project(":domain:media-detail"))

    // Feature modules
    implementation(project(":feature:entrance"))
    implementation(project(":feature:language"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:dashboard"))
    implementation(project(":feature:images"))
    implementation(project(":feature:videos"))
    implementation(project(":feature:favourites"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:media-detail"))

    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Dependency Injection
    implementation(libs.koin.android)
}