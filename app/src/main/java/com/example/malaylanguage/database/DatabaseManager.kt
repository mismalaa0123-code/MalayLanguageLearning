package com.example.malaylanguage.database

import android.content.Context
import com.example.malaylanguage.data.model.LanguageItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseManager private constructor(private val context: Context) {
    private var languageItems: List<LanguageItem> = emptyList()
    private var isLoaded = false

    companion object {
        private var instance: DatabaseManager? = null
        private const val DATABASE_FILE = "malay_language_database.json"

        fun initialize(context: Context) {
            if (instance == null) {
                instance = DatabaseManager(context)
            }
        }

        fun getInstance(): DatabaseManager {
            return instance ?: throw IllegalStateException("DatabaseManager not initialized")
        }
    }

    suspend fun loadDatabase(): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            if (isLoaded && languageItems.isNotEmpty()) {
                return@withContext true
            }

            val inputStream = context.assets.open(DATABASE_FILE)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val json = String(buffer, Charsets.UTF_8)
            val type = object : TypeToken<List<LanguageItem>>() {}.type
            languageItems = Gson().fromJson(json, type)
            isLoaded = true
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getAllItems(): List<LanguageItem> = languageItems

    fun getItemById(id: Int): LanguageItem? = languageItems.find { it.id == id }

    fun getItemsByCategory(categoryId: String): List<LanguageItem> {
        return languageItems.filter { it.categoryId == categoryId }
    }

    fun getTotalCount(): Int = languageItems.size

    fun getCategoryCount(categoryId: String): Int {
        return languageItems.count { it.categoryId == categoryId }
    }

    fun getItemsByDifficulty(difficulty: String): List<LanguageItem> {
        return languageItems.filter { it.difficulty == difficulty }
    }

    fun getRandomItems(count: Int = 10): List<LanguageItem> {
        return languageItems.shuffled().take(count)
    }

    fun isLoaded(): Boolean = isLoaded
}
