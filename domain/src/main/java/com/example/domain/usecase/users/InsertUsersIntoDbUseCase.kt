package com.example.domain.usecase.users

import com.example.domain.entity.local.UserEntity
import com.example.domain.repository.UsersRepository
import javax.inject.Inject

class InsertUsersIntoDbUseCase @Inject constructor(
  private val usersRepository: UsersRepository
) {

  suspend operator fun invoke(users: List<UserEntity>) {
    usersRepository.insertUsersIntoDb(users = users)
  }
}