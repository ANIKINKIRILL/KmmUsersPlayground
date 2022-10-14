package com.anikinkirill.kmm.domain.usecase

import com.anikinkirill.kmm.domain.model.User
import com.anikinkirill.kmm.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {

    suspend fun execute(): List<User> {
        return userRepository.getUsers()
    }
}