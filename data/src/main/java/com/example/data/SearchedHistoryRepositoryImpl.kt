package com.example.data

import com.example.data.dao.SearchedHistoryDao
import com.example.domain.entity.local.SearchedHistoryEntity
import com.example.domain.repository.SearchedHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchedHistoryRepositoryImpl @Inject constructor(private val searchedHistoryDao: SearchedHistoryDao) :
  SearchedHistoryRepository {
  override suspend fun insertSearchedQuery(searchedHistoryEntity: SearchedHistoryEntity) {
    searchedHistoryDao.insertSearchedQuery(searchedHistoryEntity)
  }

  override suspend fun deleteAllSearchedHistory() {
    searchedHistoryDao.deleteAllSearchHistory()
  }

  override suspend fun deleteSearchedHistory(searchHistoryId: Long) {
    searchedHistoryDao.deleteSearchHistory(searchHistoryId = searchHistoryId)
  }

  override fun getAllSearchedHistory(): Flow<List<SearchedHistoryEntity>> =
    searchedHistoryDao.getAllSearchHistory()
}