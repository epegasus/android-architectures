package com.sohaib.modularization.data.language.mapper

import com.sohaib.modularization.data.language.model.LanguageEntity
import com.sohaib.modularization.domain.language.entity.Language

/**
 * Mapper for converting between data and domain layers
 */
fun LanguageEntity.mapToDomain(): Language {
    return Language(
        code = code,
        name = name,
        nativeName = nativeName,
        isSelected = false,
    )
}

fun Language.mapToData(): LanguageEntity {
    return LanguageEntity(
        code = code,
        name = name,
        nativeName = nativeName,
        flag = "" // Default flag, will be set from SUPPORTED_LANGUAGES
    )
}