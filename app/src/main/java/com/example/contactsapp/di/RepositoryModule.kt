package com.example.contactsapp.di

import com.example.data.SearchedHistoryRepositoryImpl
import com.example.data.UsersRepositoryImpl
import com.example.domain.repository.SearchedHistoryRepository
import com.example.domain.repository.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepositoryModule {

  @Binds
  abstract fun bindUsersRepository(repository: UsersRepositoryImpl): UsersRepository

  @Binds
  abstract fun bindSearchedHistoryRepository(repository: SearchedHistoryRepositoryImpl): SearchedHistoryRepository
}