package com.anikinkirill.kmm.android.users

data class UsersScreenState(
    val users: List<UserViewObject>,
) {

    companion object {
        val EMPTY = UsersScreenState(
            users = emptyList(),
        )
    }
}