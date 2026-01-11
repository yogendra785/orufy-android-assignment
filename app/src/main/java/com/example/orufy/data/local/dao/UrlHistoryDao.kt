package com.example.orufy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.orufy.data.local.entity.UrlHistoryEntity
import kotlinx.coroutines.flow.Flow


@Dao

interface UrlHistoryDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrl(url: UrlHistoryEntity)

    @Query("SELECT * FROM url_history ORDER BY timestamp DESC")
    fun getAllUrls(): Flow<List<UrlHistoryEntity>>

    @Query("DELETE FROM url_history")
    suspend fun clearAll()


}