package com.example.malaylanguage.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "favorites")

class FavoriteManager(private val context: Context) {
    private val favoritesKey = stringSetPreferencesKey("favorites")

    suspend fun addFavorite(itemId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[favoritesKey]?.toMutableSet() ?: mutableSetOf()
            currentFavorites.add(itemId.toString())
            preferences[favoritesKey] = currentFavorites
        }
    }

    suspend fun removeFavorite(itemId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[favoritesKey]?.toMutableSet() ?: mutableSetOf()
            currentFavorites.remove(itemId.toString())
            preferences[favoritesKey] = currentFavorites
        }
    }

    suspend fun toggleFavorite(itemId: Int): Boolean {
        val isFavorite = isFavorite(itemId)
        if (isFavorite) {
            removeFavorite(itemId)
        } else {
            addFavorite(itemId)
        }
        return !isFavorite
    }

    suspend fun isFavorite(itemId: Int): Boolean {
        return getFavorites().contains(itemId.toString())
    }

    suspend fun getFavorites(): Set<String> {
        return context.dataStore.data.map { preferences ->
            preferences[favoritesKey] ?: emptySet()
        }.map { it }.firstOrNull() ?: emptySet()
    }

    fun getFavoritesFlow(): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[favoritesKey] ?: emptySet()
        }
    }

    suspend fun clearAllFavorites() {
        context.dataStore.edit { preferences ->
            preferences[favoritesKey] = emptySet()
        }
    }

    suspend fun getFavoriteCount(): Int {
        return getFavorites().size
    }
}
