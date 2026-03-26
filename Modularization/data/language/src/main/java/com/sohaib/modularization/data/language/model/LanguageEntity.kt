package com.sohaib.modularization.data.language.model

/**
 * Data class representing a language option
 */
data class LanguageEntity(
    val code: String,
    val name: String,
    val nativeName: String,
    val flag: String
)