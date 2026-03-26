package com.sohaib.modularization.feature.entrance.states

sealed class EntranceDestination {
    object Language : EntranceDestination()
    object OnBoarding : EntranceDestination()
    object Home : EntranceDestination()
}