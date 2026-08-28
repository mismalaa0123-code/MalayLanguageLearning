package com.example.malaylanguage.data.model

import com.google.gson.annotations.SerializedName

data class LanguageItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: String, // word, phrase, sentence, question, answer, emergency
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("bangla")
    val bangla: String,
    @SerializedName("malay")
    val malay: String,
    @SerializedName("pronunciation_bn")
    val pronunciationBn: String,
    @SerializedName("meaning")
    val meaning: String,
    @SerializedName("keywords")
    val keywords: List<String>,
    @SerializedName("difficulty")
    val difficulty: String, // beginner, intermediate, advanced
    @SerializedName("example")
    val example: Example,
    @SerializedName("audio")
    val audio: AudioInfo
) {
    fun matchesKeyword(keyword: String): Boolean {
        val lowerKeyword = keyword.lowercase()
        return bangla.lowercase().contains(lowerKeyword) ||
                keywords.any { it.lowercase().contains(lowerKeyword) } ||
                meaning.lowercase().contains(lowerKeyword)
    }
}
