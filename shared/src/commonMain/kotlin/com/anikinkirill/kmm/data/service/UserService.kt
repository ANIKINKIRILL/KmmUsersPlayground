package com.anikinkirill.kmm.data.service

import com.anikinkirill.kmm.data.model.UserDto

interface UserService {

    suspend fun getUsers(): List<UserDto>
}