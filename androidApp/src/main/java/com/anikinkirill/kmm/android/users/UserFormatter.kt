package com.anikinkirill.kmm.android.users

import com.anikinkirill.kmm.domain.model.User

class UserFormatter {

    fun format(users: List<User>): List<UserViewObject> {
        return users.map { user -> format(user = user) }
    }

    private fun format(user: User): UserViewObject {
        return UserViewObject(
            name = user.name,
            username = user.username,
        )
    }
}