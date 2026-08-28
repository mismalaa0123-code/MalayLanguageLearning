package com.example.malaylanguage.data.repository

import android.content.Context
import com.example.malaylanguage.data.local.AppDatabase
import com.example.malaylanguage.data.local.FavoriteEntity
import com.example.malaylanguage.data.local.ProgressEntity
import com.example.malaylanguage.data.model.LanguageItem
import com.example.malaylanguage.utils.JsonLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(context: Context) {
    private val db = AppDatabase.getDatabase(context)
    private val favoriteDao = db.favoriteDao()
    private val progressDao = db.progressDao()

    var allItems: List<LanguageItem> = emptyList()

    suspend fun loadDatabase() {
        withContext(Dispatchers.IO) {
            val items = JsonLoader.loadJsonFromAssets(
                // Application context needed
                db.openHelper.writableDatabase.context
            ) ?: emptyList()

            val favIds = favoriteDao.getFavoriteIds()
            items.forEach { item ->
                item.isFavorite = favIds.contains(item.id)
            }
            allItems = items
        }
    }

    suspend fun toggleFavorite(item: LanguageItem) {
        withContext(Dispatchers.IO) {
            if (item.isFavorite) {
                favoriteDao.removeFavorite(item.id)
            } else {
                favoriteDao.addFavorite(FavoriteEntity(item.id))
            }
            item.isFavorite = !item.isFavorite
        }
    }

    suspend fun markProgress(itemId: Int) {
        withContext(Dispatchers.IO) {
            progressDao.markAsRead(ProgressEntity(itemId))
        }
    }

    suspend fun getProgressCount(): Int {
        return withContext(Dispatchers.IO) {
            progressDao.getProgressCount()
        }
    }
}
