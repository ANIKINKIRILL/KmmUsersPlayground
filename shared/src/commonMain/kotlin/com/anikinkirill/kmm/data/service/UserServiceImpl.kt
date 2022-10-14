package com.anikinkirill.kmm.data.service

import com.anikinkirill.kmm.data.model.UserDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class UserServiceImpl(private val httpClient: HttpClient): UserService {

    override suspend fun getUsers(): List<UserDto> {
        return httpClient.get("https://jsonplaceholder.typicode.com/users").body()
    }
}