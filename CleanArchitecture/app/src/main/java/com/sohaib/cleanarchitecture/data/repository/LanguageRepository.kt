package com.sohaib.cleanarchitecture.data.repository

import com.sohaib.cleanarchitecture.data.datasources.DpLanguage
import com.sohaib.cleanarchitecture.domain.entities.Language

class LanguageRepository {

    fun getLanguages(): List<Language> {
        val dpLanguage = DpLanguage()
        return dpLanguage.languageList
    }
}