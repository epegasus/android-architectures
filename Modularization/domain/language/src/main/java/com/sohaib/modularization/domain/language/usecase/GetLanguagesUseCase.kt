package com.sohaib.modularization.domain.language.usecase

import com.sohaib.modularization.domain.language.entity.Language
import com.sohaib.modularization.domain.language.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting all available languages
 */
class GetLanguagesUseCase(private val languageRepository: LanguageRepository) {

    suspend operator fun invoke(): Flow<List<Language>> {
        return languageRepository.getSupportedLanguages()
    }
}