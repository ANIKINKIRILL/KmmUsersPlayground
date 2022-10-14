package com.anikinkirill.kmm.android.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anikinkirill.kmm.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val userFormatter: UserFormatter,
) : ViewModel() {

    private val _screenState = MutableStateFlow(UsersScreenState.EMPTY)
    val screenState: StateFlow<UsersScreenState> = _screenState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(), UsersScreenState.EMPTY
    )

    fun loadUsers() {
        viewModelScope.launch {
            val users = getUsersUseCase.execute()
            val userViewObjects = userFormatter.format(users = users)
            _screenState.value = _screenState.value.copy(
                users = userViewObjects
            )
        }
    }
}