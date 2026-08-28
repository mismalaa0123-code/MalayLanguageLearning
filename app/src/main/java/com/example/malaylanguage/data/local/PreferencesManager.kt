package com.example.malaylanguage.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

import androidx.datastore.core.DataStore

class PreferencesManager(private val context: Context) {
    private val darkModeKey = stringPreferencesKey("dark_mode")
    private val totalLearnedKey = intPreferencesKey("total_learned")
    private val lastOpenedKey = stringPreferencesKey("last_opened")

    suspend fun setDarkMode(enabled: String) {
        context.dataStore.edit { preferences ->
            preferences[darkModeKey] = enabled
        }
    }

    fun getDarkModeFlow(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[darkModeKey] ?: "system"
        }
    }

    suspend fun getDarkMode(): String {
        return context.dataStore.data.map { preferences ->
            preferences[darkModeKey] ?: "system"
        }.map { it }.firstOrNull() ?: "system"
    }

    suspend fun incrementLearningCount() {
        context.dataStore.edit { preferences ->
            val current = preferences[totalLearnedKey] ?: 0
            preferences[totalLearnedKey] = current + 1
        }
    }

    suspend fun getTotalLearned(): Int {
        return context.dataStore.data.map { preferences ->
            preferences[totalLearnedKey] ?: 0
        }.map { it }.firstOrNull() ?: 0
    }

    suspend fun setLastOpened(date: String) {
        context.dataStore.edit { preferences ->
            preferences[lastOpenedKey] = date
        }
    }

    suspend fun getLastOpened(): String {
        return context.dataStore.data.map { preferences ->
            preferences[lastOpenedKey] ?: ""
        }.map { it }.firstOrNull() ?: ""
    }

    suspend fun clearAllSettings() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
