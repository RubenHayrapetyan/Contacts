package com.example.domain.entity.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchedHistoryEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val query: String = ""
)
