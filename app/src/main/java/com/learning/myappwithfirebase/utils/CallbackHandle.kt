package com.learning.myappwithfirebase.utils

data class CallbackHandle<T>(
    val onSuccess: (T) -> Unit,
    val onError: (ErrorData?) -> Unit
)

data class ErrorData(val code: String, val message: String)