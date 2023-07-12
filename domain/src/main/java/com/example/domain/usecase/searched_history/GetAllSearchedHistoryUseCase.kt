package com.example.domain.usecase.searched_history

import com.example.domain.entity.local.SearchedHistoryEntity
import com.example.domain.repository.SearchedHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSearchedHistoryUseCase @Inject constructor(
  private val searchedHistoryRepository: SearchedHistoryRepository
) {

  operator fun invoke(): Flow<List<SearchedHistoryEntity>> =
    searchedHistoryRepository.getAllSearchedHistory()
}