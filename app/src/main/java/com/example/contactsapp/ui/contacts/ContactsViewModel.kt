package com.example.contactsapp.ui.contacts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.local.SearchedHistoryEntity
import com.example.domain.entity.local.UserEntity
import com.example.domain.usecase.searched_history.SearchedHistoryMergedUseCases
import com.example.domain.usecase.users.GetUsersFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
  private val getUsersFromDbUseCase: GetUsersFromDbUseCase,
  private val searchedHistoryMergedUseCases: SearchedHistoryMergedUseCases
) : ViewModel() {

  private val _users by lazy { mutableStateOf<List<UserEntity>>(emptyList()) }
  val users: State<List<UserEntity>> = _users

  private val _searchedHistory by lazy { mutableStateOf<List<SearchedHistoryEntity>>(emptyList()) }
  val searchedHistory: State<List<SearchedHistoryEntity>> = _searchedHistory

  private val _searchedUserNotFound by lazy { mutableStateOf(false) }
  val searchedUserNotFound: State<Boolean> = _searchedUserNotFound

  val searchedQuery by lazy { mutableStateOf("") }
  val isActive by lazy { mutableStateOf(false) }
  val isCloseIcon by lazy { mutableStateOf(searchedQuery.value.isNotEmpty()) }

  init {
    getUsersFromDb()
  }

  fun getUsersFromDb() {
    getUsersFromDbUseCase().onEach { userList ->
      _users.value = userList
    }.launchIn(viewModelScope)
  }

  fun insertQueryIntoDbAndGetAllSearchedHistory(query: String) {
    viewModelScope.launch {
      if (query.isEmpty()) return@launch
      searchedQuery.value = query
      val searchedQuery = SearchedHistoryEntity(query = query)
      searchedHistoryMergedUseCases.insertQueryUseCase(searchedHistoryEntity = searchedQuery)
      getAllSearchedHistory()
    }
  }

  fun getAllSearchedHistory() {
    viewModelScope.launch {
      searchedHistoryMergedUseCases.getAllSearchedHistoryUseCase().collect {
        _searchedHistory.value = it
      }
    }
  }

  fun deleteAllSearchedHistory() {
    viewModelScope.launch {
      searchedHistoryMergedUseCases.deleteAllSearchedHistoryUseCase()
      getAllSearchedHistory()
    }
  }

  fun deleteSearchedHistory(searchedHistoryId: Long) {
    viewModelScope.launch {
      searchedHistoryMergedUseCases.deleteSearchedHistoryUseCase(searchedHistoryId = searchedHistoryId)
      getAllSearchedHistory()
    }
  }

  fun getSearchedUsersFromDb(searchedQuery: String) {
    getUsersFromDbUseCase(
      query = searchedQuery
        .trim()
        .replaceFirstChar(Char::titlecase)
    )
      .onEach { userList ->
        if (userList.isEmpty()) {
          _searchedUserNotFound.value = true
        } else {
          _searchedUserNotFound.value = false
          _users.value = userList
        }
      }.launchIn(viewModelScope)
  }
}