package com.example.malaylanguage.utils

import com.example.malaylanguage.data.model.LanguageItem

object SearchManager {
    fun search(items: List<LanguageItem>, query: String): List<LanguageItem> {
        if (query.isBlank()) return emptyList()
        val lowerQuery = query.lowercase()

        return items.filter { item ->
            item.bangla.lowercase().contains(lowerQuery) ||
            item.malay.lowercase().contains(lowerQuery) ||
            item.keywords.any { keyword -> keyword.lowercase().contains(lowerQuery) }
        }
    }
}
