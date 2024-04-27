package com.learning.myappwithfirebase.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.myappwithfirebase.domain.model.UserLogged
import com.learning.myappwithfirebase.domain.repository.AuthRepository
import com.learning.myappwithfirebase.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    var userLogged by mutableStateOf(UserLogged())

    fun getCurrentUser() {
        authRepository.getCurrentUser(CallbackHandle(
            onSuccess = {
                userLogged = userLogged.copy(email = it.email)
            },
            onError = {}
        ))
    }

    fun logout(logoutResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            authRepository.logout(CallbackHandle(
                onSuccess = {
                    logoutResult.invoke(it)
                },
                onError = {}
            ))
        }
    }

}