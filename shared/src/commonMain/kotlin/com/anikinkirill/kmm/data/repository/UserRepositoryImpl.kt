package com.anikinkirill.kmm.data.repository

import com.anikinkirill.kmm.data.mapper.UserMapper
import com.anikinkirill.kmm.data.service.UserService
import com.anikinkirill.kmm.domain.model.User
import com.anikinkirill.kmm.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remote: UserService,
    private val mapper: UserMapper,
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        val users = remote.getUsers()
        return mapper.map(users = users)
    }
}