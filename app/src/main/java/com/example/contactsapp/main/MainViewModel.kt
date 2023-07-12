package com.example.contactsapp.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.users.GetUsersFromApiUseCase
import com.example.domain.usecase.users.InsertUsersIntoDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val getUsersFromApiUseCase: GetUsersFromApiUseCase,
  private val insertUsersIntoDbUseCase: InsertUsersIntoDbUseCase
  ) : ViewModel() {

  private val _loading = mutableStateOf(true)
  val loading: State<Boolean> = _loading

  init {
    getUsersFromApi()
  }

  private fun getUsersFromApi() {
    getUsersFromApiUseCase().onEach { users ->
      insertUsersIntoDbUseCase(users = users)
      _loading.value = false
    }.launchIn(viewModelScope)
  }
}