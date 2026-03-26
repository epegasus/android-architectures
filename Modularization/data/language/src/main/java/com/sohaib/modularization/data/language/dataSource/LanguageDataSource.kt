package com.sohaib.modularization.data.language.dataSource

import com.sohaib.modularization.data.language.model.LanguageEntity

class LanguageDataSource {

    fun getLanguages(): List<LanguageEntity> {
        return listOf(
            LanguageEntity("en", "English", "English", "🇺🇸"),
            LanguageEntity("es", "Spanish", "Español", "🇪🇸"),
            LanguageEntity("fr", "French", "Français", "🇫🇷"),
            LanguageEntity("de", "German", "Deutsch", "🇩🇪"),
            LanguageEntity("it", "Italian", "Italiano", "🇮🇹"),
            LanguageEntity("pt", "Portuguese", "Português", "🇵🇹"),
            LanguageEntity("ru", "Russian", "Русский", "🇷🇺"),
            LanguageEntity("zh", "Chinese", "中文", "🇨🇳"),
            LanguageEntity("ja", "Japanese", "日本語", "🇯🇵"),
            LanguageEntity("ko", "Korean", "한국어", "🇰🇷"),
            LanguageEntity("ar", "Arabic", "العربية", "🇸🇦"),
            LanguageEntity("hi", "Hindi", "हिन्दी", "🇮🇳")
        )
    }
}