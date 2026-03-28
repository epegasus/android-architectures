package com.sohaib.cleanarchitecture.domain.usecases

import com.sohaib.cleanarchitecture.data.repository.LanguageRepository
import com.sohaib.cleanarchitecture.domain.entities.Language

class UseCaseLanguage(private val languageRepository: LanguageRepository) {

    fun fetchLanguages(): List<Language> {
        // Business Logic
        val allLanguages = languageRepository.getLanguages()
        allLanguages.find { it.languageCode == "en" }?.isSelected = true
        return allLanguages
    }

    fun updateLanguages(selectedCode: String): List<Language> {
        val allLanguages = languageRepository.getLanguages()
        allLanguages.find { it.languageCode == selectedCode }?.isSelected = true
        return allLanguages
    }
}