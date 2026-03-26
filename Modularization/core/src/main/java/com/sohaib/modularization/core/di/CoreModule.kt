package com.sohaib.modularization.core.di

import com.sohaib.modularization.core.database.AppDatabase
import com.sohaib.modularization.core.mediastore.MediaStoreManager
import com.sohaib.modularization.core.mediastore.PermissionManager
import com.sohaib.modularization.core.sharedPreferences.SharedPrefManager
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Core module for dependency injection
 */
val coreModule = module {

    // Database
    single { AppDatabase.getDatabase(androidContext()) }
    single { get<AppDatabase>().favouritesDao() }

    // Coroutine Dispatcher
    single { Dispatchers.IO }
    single { Dispatchers.Default }

    // Shared Preferences
    single { SharedPrefManager(androidContext()) }

    // MediaStore
    single { MediaStoreManager(androidContext()) }
    single { PermissionManager(androidContext()) }
}