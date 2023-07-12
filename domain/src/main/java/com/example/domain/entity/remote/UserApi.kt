package com.example.domain.entity.remote

data class UserApi(
  val id: Long? = 0,
  val image: Int? = 0,
  val name: String? = null,
  val phoneNumber: String? = null,
  val description: String? = null
)
