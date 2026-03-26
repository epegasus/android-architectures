pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Modularization"

// Core modules
include(":core")
include(":core:database")
include(":core:mediastore")
include(":core:navigation")
include(":core:theme")

// Data modules
include(":data:entrance")
include(":data:language")
include(":data:onboarding")
include(":data:images")
include(":data:videos")
include(":data:favourites")
include(":data:settings")
include(":data:media-detail")

// Domain modules
include(":domain:entrance")
include(":domain:language")
include(":domain:onboarding")
include(":domain:images")
include(":domain:videos")
include(":domain:favourites")
include(":domain:settings")
include(":domain:media-detail")

// Feature modules
include(":feature:entrance")
include(":feature:language")
include(":feature:onboarding")
include(":feature:dashboard")
include(":feature:images")
include(":feature:videos")
include(":feature:favourites")
include(":feature:settings")
include(":feature:media-detail")

// App module
include(":app")