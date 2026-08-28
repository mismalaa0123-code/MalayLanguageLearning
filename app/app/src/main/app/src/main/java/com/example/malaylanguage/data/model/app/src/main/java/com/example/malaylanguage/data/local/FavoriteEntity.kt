package com.example.malaylanguage.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val itemId: Int
)

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey val itemId: Int
)
