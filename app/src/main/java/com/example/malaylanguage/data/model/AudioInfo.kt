package com.example.malaylanguage.data.model

import com.google.gson.annotations.SerializedName

data class AudioInfo(
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("tts_language")
    val ttsLanguage: String = "ms-MY"
)
