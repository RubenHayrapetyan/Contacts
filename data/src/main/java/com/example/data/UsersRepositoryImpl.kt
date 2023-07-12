package com.example.data

import com.example.data.dao.UserDao
import com.example.domain.entity.local.UserEntity
import com.example.domain.entity.remote.UserApi
import com.example.domain.mappers.toUserLocal
import com.example.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
  private val userDao: UserDao
) : UsersRepository {

  override suspend fun insertUsersIntoDb(users: List<UserEntity>) {
    userDao.insertUsers(users = users.toTypedArray())
  }

  override fun getUsersFromApi(): Flow<List<UserEntity>> {
    val users = listOf(
      UserApi(
        id = 1,
        image = 2131165411,
        name = "Vazgen",
        phoneNumber = "+7 890 765",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
      ),
      UserApi(
        id = 2,
        image = 2131165412,
        name = "Vardges",
        phoneNumber = "+222 457 333",
        description = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
      ),
      UserApi(
        id = 3,
        image = 2131165413,
        name = "Sara",
        phoneNumber = "+245 56 4444",
        description = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
      ),
      UserApi(
        id = 4,
        image = 2131165414,
        name = "Ashley",
        phoneNumber = "+2 457 333 444",
        description = "Eget duis at tellus at urna condimentum. Nulla pellentesque dignissim enim sit amet venenatis urna cursus eget. Massa tempor nec feugiat nisl pretium fusce id velit ut."
      ),
    ).map { it.toUserLocal() }

    return flowOf(users)
  }

  override fun getUsersFromDb(query: String?): Flow<List<UserEntity>> {
    return if (query == null) {
      userDao.getAllUsers()
    } else {
      userDao.getSearchedUsers(query = query)
    }
  }

  override suspend fun getUserFromDb(userName: String): UserEntity? =
    userDao.getUserByName(userName = userName)
}