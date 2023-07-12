package com.example.domain.entity.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0,
  val image: Int = 0,
  val name: String = "",
  val phoneNumber: String = "",
  val description: String = ""
)
