package com.anikinkirill.kmm.domain.repository

import com.anikinkirill.kmm.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}