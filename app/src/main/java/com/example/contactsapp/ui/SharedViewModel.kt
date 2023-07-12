package com.example.contactsapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.entity.local.UserEntity

class SharedViewModel: ViewModel() {

  var user by mutableStateOf<UserEntity?>(null)
    private set

  fun addUser(userEntity: UserEntity){
    user = userEntity
  }
}