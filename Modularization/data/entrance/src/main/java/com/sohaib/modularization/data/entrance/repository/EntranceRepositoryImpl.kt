package com.sohaib.modularization.data.entrance.repository

import com.sohaib.modularization.core.sharedPreferences.SharedPrefManager
import com.sohaib.modularization.domain.entrance.repository.EntranceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of LanguageRepository
 */
class EntranceRepositoryImpl(
    private val sharedPrefManager: SharedPrefManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : EntranceRepository {

    override suspend fun isLanguageSelected(): Boolean = withContext(ioDispatcher) {
        sharedPrefManager.currentLanguage != "en"
    }

    override suspend fun isOnBoardingCompleted(): Boolean = withContext(ioDispatcher) {
        sharedPrefManager.markOnBoardingComplete
    }
}