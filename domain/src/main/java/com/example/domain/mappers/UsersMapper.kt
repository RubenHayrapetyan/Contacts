package com.example.domain.mappers

import com.example.domain.entity.local.UserEntity
import com.example.domain.entity.remote.UserApi

fun UserApi.toUserLocal() = UserEntity(
  id = id ?: 0,
  image = image ?: 0,
  name = name ?: "",
  phoneNumber = phoneNumber ?: "",
  description = description ?: ""
)