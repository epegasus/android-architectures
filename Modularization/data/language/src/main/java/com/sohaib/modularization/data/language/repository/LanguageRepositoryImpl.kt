package com.sohaib.modularization.data.language.repository

import com.sohaib.modularization.core.sharedPreferences.SharedPrefManager
import com.sohaib.modularization.data.language.dataSource.LanguageDataSource
import com.sohaib.modularization.data.language.mapper.mapToDomain
import com.sohaib.modularization.domain.language.entity.Language
import com.sohaib.modularization.domain.language.repository.LanguageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

/**
 * Implementation of LanguageRepository
 */
class LanguageRepositoryImpl(
    private val dataSource: LanguageDataSource,
    private val sharedPrefManager: SharedPrefManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LanguageRepository {

    override suspend fun getSupportedLanguages(): Flow<List<Language>> = flow {
        val languages = dataSource.getLanguages().map { it.mapToDomain() }
        emit(languages)
    }.flowOn(ioDispatcher)

    override suspend fun getCurrentLanguage(): Flow<Language> = flow {
        val currentCode = sharedPrefManager.currentLanguage
        val language = getLanguageByCode(currentCode) ?: dataSource.getLanguages().first().mapToDomain()
        emit(language)
    }.flowOn(ioDispatcher)

    override suspend fun setCurrentLanguage(languageCode: String) = withContext(ioDispatcher) {
        sharedPrefManager.currentLanguage = languageCode
    }

    override suspend fun getLanguageByCode(code: String): Language? = withContext(ioDispatcher) {
        dataSource.getLanguages().find { it.code == code }?.mapToDomain()
    }
}