package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entity.local.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertUsers(vararg users: UserEntity)

  @Query("SELECT * FROM UserEntity")
  fun getAllUsers(): Flow<List<UserEntity>>

  @Query("SELECT * FROM UserEntity WHERE name LIKE '%' || :query || '%'")
  fun getSearchedUsers(query: String): Flow<List<UserEntity>>

  @Query("SELECT * FROM UserEntity WHERE name = :userName")
  suspend fun getUserByName(userName: String): UserEntity?
}