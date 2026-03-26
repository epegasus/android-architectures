package com.sohaib.modularization.domain.language.repository

import com.sohaib.modularization.domain.language.entity.Language
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for language data operations
 */
interface LanguageRepository {

    /**
     * Get all supported languages
     */
    suspend fun getSupportedLanguages(): Flow<List<Language>>

    /**
     * Get current selected language
     */
    suspend fun getCurrentLanguage(): Flow<Language>

    /**
     * Set current language
     */
    suspend fun setCurrentLanguage(languageCode: String)

    /**
     * Get language by code
     */
    suspend fun getLanguageByCode(code: String): Language?
}