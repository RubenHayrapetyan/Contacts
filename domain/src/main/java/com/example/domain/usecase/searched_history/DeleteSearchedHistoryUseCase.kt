package com.example.domain.usecase.searched_history

import com.example.domain.repository.SearchedHistoryRepository
import javax.inject.Inject

class DeleteSearchedHistoryUseCase @Inject constructor(
  private val searchedHistoryRepository: SearchedHistoryRepository
) {

  suspend operator fun invoke(searchedHistoryId: Long) {
    searchedHistoryRepository.deleteSearchedHistory(searchHistoryId = searchedHistoryId)
  }
}