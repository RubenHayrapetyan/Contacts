package com.example.domain.usecase.searched_history

import com.example.domain.repository.SearchedHistoryRepository
import javax.inject.Inject

class SearchedHistoryMergedUseCases @Inject constructor(searchedHistoryRepository: SearchedHistoryRepository) {

  val insertQueryUseCase by lazy { InsertQueryUseCase(searchedHistoryRepository) }
  val deleteSearchedHistoryUseCase by lazy { DeleteSearchedHistoryUseCase(searchedHistoryRepository) }
  val deleteAllSearchedHistoryUseCase by lazy { DeleteAllSearchedHistoryUseCase(searchedHistoryRepository) }
  val getAllSearchedHistoryUseCase by lazy { GetAllSearchedHistoryUseCase(searchedHistoryRepository) }
}