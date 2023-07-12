package com.example.domain.usecase.users

import com.example.domain.entity.local.UserEntity
import com.example.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersFromDbUseCase @Inject constructor(private val usersRepository: UsersRepository) {

  operator fun invoke(query: String? = null): Flow<List<UserEntity>> =
    usersRepository.getUsersFromDb(query = query)
}