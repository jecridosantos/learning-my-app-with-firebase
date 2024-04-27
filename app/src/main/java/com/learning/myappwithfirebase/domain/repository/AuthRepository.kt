package com.learning.myappwithfirebase.domain.repository

import com.learning.myappwithfirebase.domain.model.UserLogged
import com.learning.myappwithfirebase.utils.CallbackHandle

interface AuthRepository {
    fun getCurrentUser(callback : CallbackHandle<UserLogged>)

}