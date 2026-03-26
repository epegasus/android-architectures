package com.sohaib.modularization.domain.language.usecase

import com.sohaib.modularization.domain.language.repository.LanguageRepository

/**
 * Use case for setting current language
 */
class SetLanguageUseCase(private val languageRepository: LanguageRepository) {
    
    suspend operator fun invoke(languageCode: String) {
        languageRepository.setCurrentLanguage(languageCode)
    }
}
