package com.example.orufy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "url_history")
data class UrlHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id :Long = 0,
    val url: String,
    val timestamp: Long
)
