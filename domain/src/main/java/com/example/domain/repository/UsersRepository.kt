package com.example.domain.repository

import com.example.domain.entity.local.UserEntity
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

  fun getUsersFromApi(): Flow<List<UserEntity>>

  fun getUsersFromDb(query: String? = null): Flow<List<UserEntity>>

  suspend fun getUserFromDb(userName: String): UserEntity?

  suspend fun insertUsersIntoDb(users: List<UserEntity>)
}