package com.example.domain.repository

import com.example.domain.entity.local.SearchedHistoryEntity
import kotlinx.coroutines.flow.Flow

interface SearchedHistoryRepository {

  suspend fun insertSearchedQuery(searchedHistoryEntity: SearchedHistoryEntity)

  suspend fun deleteAllSearchedHistory()

  suspend fun deleteSearchedHistory(searchHistoryId: Long)

  fun getAllSearchedHistory(): Flow<List<SearchedHistoryEntity>>
}