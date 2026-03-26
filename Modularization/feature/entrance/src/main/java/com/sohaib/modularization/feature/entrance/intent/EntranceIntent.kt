package com.sohaib.modularization.feature.entrance.intent

sealed class EntranceIntent {
    object OnGetStartedClicked : EntranceIntent()
}