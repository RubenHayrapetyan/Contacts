package com.example.domain.usecase.searched_history

import com.example.domain.entity.local.SearchedHistoryEntity
import com.example.domain.repository.SearchedHistoryRepository
import javax.inject.Inject

class InsertQueryUseCase @Inject constructor(
  private val searchedHistoryRepository: SearchedHistoryRepository
) {

   suspend operator fun invoke(searchedHistoryEntity: SearchedHistoryEntity) {
    searchedHistoryRepository.insertSearchedQuery(searchedHistoryEntity = searchedHistoryEntity)
  }
}