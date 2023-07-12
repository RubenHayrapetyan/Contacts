package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entity.local.SearchedHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchedHistoryDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSearchedQuery(searchedHistoryEntity: SearchedHistoryEntity)

  @Query("DELETE FROM SearchedHistoryEntity")
  suspend fun deleteAllSearchHistory()

  @Query("DELETE FROM SearchedHistoryEntity WHERE id = :searchHistoryId")
  suspend fun deleteSearchHistory(searchHistoryId: Long)

  @Query("SELECT * FROM SearchedHistoryEntity")
  fun getAllSearchHistory(): Flow<List<SearchedHistoryEntity>>
}