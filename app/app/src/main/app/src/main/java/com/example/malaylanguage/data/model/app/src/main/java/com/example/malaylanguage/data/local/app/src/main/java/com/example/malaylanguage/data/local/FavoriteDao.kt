package com.example.malaylanguage.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE itemId = :id")
    suspend fun removeFavorite(id: Int)

    @Query("SELECT itemId FROM favorites")
    suspend fun getFavoriteIds(): List<Int>
}

@Dao
interface ProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markAsRead(progress: ProgressEntity)

    @Query("SELECT COUNT(itemId) FROM progress")
    suspend fun getProgressCount(): Int
}
