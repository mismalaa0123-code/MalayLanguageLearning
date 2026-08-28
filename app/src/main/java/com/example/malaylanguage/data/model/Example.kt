package com.example.malaylanguage.data.model

import com.google.gson.annotations.SerializedName

data class Example(
    @SerializedName("bangla")
    val bangla: String,
    @SerializedName("malay")
    val malay: String,
    @SerializedName("pronunciation_bn")
    val pronunciationBn: String
)
