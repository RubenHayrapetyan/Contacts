package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.SearchedHistoryDao
import com.example.data.dao.UserDao
import com.example.domain.entity.local.SearchedHistoryEntity
import com.example.domain.entity.local.UserEntity

@Database(entities = [UserEntity::class, SearchedHistoryEntity::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
  abstract fun userDao(): UserDao
  abstract fun searchHistoryDao(): SearchedHistoryDao
}