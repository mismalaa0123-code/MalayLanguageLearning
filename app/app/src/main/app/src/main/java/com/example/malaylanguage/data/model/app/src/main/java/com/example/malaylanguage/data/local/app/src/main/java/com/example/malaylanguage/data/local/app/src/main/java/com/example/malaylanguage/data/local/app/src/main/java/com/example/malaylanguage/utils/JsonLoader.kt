package com.example.malaylanguage.utils

import android.content.Context
import com.example.malaylanguage.data.model.LanguageItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object JsonLoader {
    fun loadJsonFromAssets(context: Context): List<LanguageItem>? {
        val gson = Gson()
        val jsonString: String
        try {
            jsonString = context.assets.open("malay_language_database.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        val listType = object : TypeToken<List<LanguageItem>>() {}.type
        return gson.fromJson(jsonString, listType)
    }
}
