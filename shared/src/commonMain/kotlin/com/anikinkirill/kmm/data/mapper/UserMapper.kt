package com.anikinkirill.kmm.data.mapper

import com.anikinkirill.kmm.domain.model.User
import com.anikinkirill.kmm.data.model.UserDto

class UserMapper {

    fun map(users: List<UserDto>): List<User> {
        return users.map { user -> map(user = user) }
    }

    private fun map(user: UserDto): User {
        return User(
            id = user.id ?: -1,
            username = user.username.orEmpty(),
            name = user.name.orEmpty(),
        )
    }
}