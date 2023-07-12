package com.example.domain.usecase.searched_history

import com.example.domain.repository.SearchedHistoryRepository
import javax.inject.Inject

class DeleteAllSearchedHistoryUseCase @Inject constructor(
  private val searchedHistoryRepository: SearchedHistoryRepository
) {

  suspend operator fun invoke() {
    searchedHistoryRepository.deleteAllSearchedHistory()
  }
}