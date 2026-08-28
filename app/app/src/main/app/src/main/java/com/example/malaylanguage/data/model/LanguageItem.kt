package com.example.malaylanguage.data.model

data class LanguageItem(
    val id: Int,
    val type: String,
    val category_id: String,
    val bangla: String,
    val malay: String,
    val pronunciation_bn: String,
    val meaning: String,
    val keywords: List<String>,
    val difficulty: String,
    val example: Example,
    val audio: AudioInfo,
    var isFavorite: Boolean = false
)

data class Example(
    val bangla: String,
    val malay: String,
    val pronunciation_bn: String
)

data class AudioInfo(
    val enabled: Boolean,
    val tts_language: String
)

data class Category(
    val id: String,
    val nameBn: String,
    val icon: String
)
