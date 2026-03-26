package com.sohaib.modularization.domain.language.usecase

import com.sohaib.modularization.domain.language.entity.Language
import com.sohaib.modularization.domain.language.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting current selected language
 */
class GetCurrentLanguageUseCase(private val languageRepository: LanguageRepository) {

    suspend operator fun invoke(): Flow<Language> {
        return languageRepository.getCurrentLanguage()
    }
}