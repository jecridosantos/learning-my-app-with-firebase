package com.learning.myappwithfirebase.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.learning.myappwithfirebase.domain.repository.AuthRepository
import com.learning.myappwithfirebase.utils.CallbackHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    var isLogged by mutableStateOf(false)

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        authRepository.getCurrentUser(CallbackHandle(
            onSuccess = { isLogged = true },
            onError = {}
        ))
    }
}