package com.learning.myappwithfirebase.domain.repository

import com.learning.myappwithfirebase.domain.model.UserLogged
import com.learning.myappwithfirebase.utils.CallbackHandle

interface AuthRepository {
    fun getCurrentUser(callback : CallbackHandle<UserLogged>)

    suspend fun signInWithEmailAndPassword(email: String, password: String, callback: CallbackHandle<Boolean>)

    suspend fun signInWithGoogle(tokenId: String, callback: CallbackHandle<Boolean>)

    fun logout(callback: CallbackHandle<Boolean>)
}