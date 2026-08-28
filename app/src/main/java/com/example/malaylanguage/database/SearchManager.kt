package com.example.malaylanguage.database

import com.example.malaylanguage.data.model.LanguageItem

class SearchManager(private val items: List<LanguageItem>) {

    fun search(query: String): List<LanguageItem> {
        if (query.isBlank()) {
            return emptyList()
        }

        val lowerQuery = query.lowercase().trim()
        return items.filter { item ->
            item.bangla.lowercase().contains(lowerQuery) ||
            item.meaning.lowercase().contains(lowerQuery) ||
            item.keywords.any { it.lowercase().contains(lowerQuery) }
        }
    }

    fun searchByCategory(query: String, categoryId: String): List<LanguageItem> {
        val categoryItems = items.filter { it.categoryId == categoryId }
        return search(query).filter { it.categoryId == categoryId }
    }

    fun advancedSearch(
        query: String,
        categoryId: String? = null,
        difficulty: String? = null,
        type: String? = null
    ): List<LanguageItem> {
        var results = search(query)

        categoryId?.let { results = results.filter { it.categoryId == categoryId } }
        difficulty?.let { results = results.filter { it.difficulty == difficulty } }
        type?.let { results = results.filter { it.type == type } }

        return results
    }

    fun getItemsContainingKeyword(keyword: String): List<LanguageItem> {
        val lowerKeyword = keyword.lowercase()
        return items.filter { item ->
            item.keywords.any { it.lowercase().contains(lowerKeyword) }
        }
    }

    fun searchWithFuzzyMatch(query: String): List<LanguageItem> {
        val lowerQuery = query.lowercase()
        return items.filter { item ->
            calculateSimilarity(item.bangla.lowercase(), lowerQuery) > 0.6 ||
            item.keywords.any { calculateSimilarity(it.lowercase(), lowerQuery) > 0.6 }
        }
    }

    private fun calculateSimilarity(s1: String, s2: String): Double {
        val longer = if (s1.length > s2.length) s1 else s2
        val shorter = if (longer.length == s1.length) s2 else s1

        if (longer.isEmpty()) return 1.0
        val editDistance = computeEditDistance(longer, shorter)
        return (longer.length - editDistance) / longer.length.toDouble()
    }

    private fun computeEditDistance(s1: String, s2: String): Int {
        val costs = IntArray(s2.length + 1)
        for (i in 0..s1.length) {
            var nw = i
            for (j in 0..s2.length) {
                val cj = if (s1[i - 1] == s2[j - 1]) nw else (1 + minOf(nw, costs[j], costs[j - 1]))
                costs[j - 1] = nw
                nw = cj
            }
            costs[s2.length] = nw
        }
        return costs[s2.length]
    }
}
