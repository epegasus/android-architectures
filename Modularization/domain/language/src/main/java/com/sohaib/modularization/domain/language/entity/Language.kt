package com.sohaib.modularization.domain.language.entity

/**
 * Domain entity representing a language
 */
data class Language(
    val code: String,
    val name: String,
    val nativeName: String,
    val isSelected: Boolean = false,
    val itemClick: () -> Unit = {}
)