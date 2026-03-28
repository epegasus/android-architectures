package com.sohaib.cleanarchitecture.domain.entities

data class Language(
    val id: Int,
    val languageCode: String,
    val languageName: String,
    var isSelected: Boolean = false,
)