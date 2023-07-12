package com.example.domain.usecase.users

import com.example.domain.entity.local.UserEntity
import com.example.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersFromApiUseCase @Inject constructor(private val usersRepository: UsersRepository) {

  operator fun invoke(): Flow<List<UserEntity>> = usersRepository.getUsersFromApi()
}