package com.sohaib.modularization.core.flows

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

inline fun LifecycleOwner.repeatWhenStarted(crossinline block: suspend () -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}

inline fun <T> LifecycleOwner.collectFlow(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline collector: suspend (T) -> Unit
) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(state) {
            flow.collect { collector(it) }
        }
    }
}

inline fun <T> LifecycleOwner.observeStates(
    flow: Flow<T>,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline builder: Flow<T>.() -> Flow<T> = { this },
    collectLatestMode: Boolean = false,
    noinline block: suspend CoroutineScope.(T) -> Unit
): Job = lifecycleScope.launch {
    lifecycle.repeatOnLifecycle(minActiveState) {
        val built = flow.builder()
        if (collectLatestMode) {
            built.collectLatest { value -> block(value) }
        } else {
            built.collect { value -> block(value) }
        }
    }
}