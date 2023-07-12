package com.example.contactsapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.dao.SearchedHistoryDao
import com.example.data.dao.UserDao
import com.example.data.local.ContactsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object RoomModule {
  @Provides
  fun provideYourDatabase(@ApplicationContext context: Context): ContactsDatabase =
    Room.databaseBuilder(
      context,
      ContactsDatabase::class.java,
      "contacts_db"
    ).build()

  @Provides
  fun provideUserDao(database: ContactsDatabase): UserDao = database.userDao()

  @Provides
  fun provideSearchDao(database: ContactsDatabase): SearchedHistoryDao = database.searchHistoryDao()
}