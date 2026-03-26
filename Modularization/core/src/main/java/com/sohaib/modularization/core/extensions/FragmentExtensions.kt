package com.sohaib.modularization.core.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.popFrom(fragmentId: Int) {
    launchWhenCreated {
        if (isAdded && isCurrentDestination(fragmentId)) {
            findNavController().popBackStack()
        }
    }
}

fun Fragment.isCurrentDestination(fragmentId: Int): Boolean {
    return findNavController().currentDestination?.id == fragmentId
}