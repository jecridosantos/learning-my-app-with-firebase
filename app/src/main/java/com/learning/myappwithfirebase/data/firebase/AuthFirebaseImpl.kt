package com.learning.myappwithfirebase.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.learning.myappwithfirebase.domain.model.UserLogged
import com.learning.myappwithfirebase.domain.repository.AuthRepository
import com.learning.myappwithfirebase.utils.CallbackHandle
import javax.inject.Inject

class AuthFirebaseImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {
    override fun getCurrentUser(callback: CallbackHandle<UserLogged>) {
        try {
            val currentUser = auth.currentUser
            val userLogged = UserLogged(
                uid = currentUser!!.uid,
                email = currentUser.email.toString()
            )
            callback.onSuccess.invoke(userLogged)
        } catch (e: Exception) {
            callback.onError.invoke(null)
        }

    }

}