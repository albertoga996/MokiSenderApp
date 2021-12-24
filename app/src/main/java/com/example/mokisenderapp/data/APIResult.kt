package com.example.mokisenderapp.data

sealed class APIResult<out T> {

    data class Success<out T>(val data: T) : APIResult<T>()
    data class Error(val apiError: String) : APIResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[error=$apiError]"
        }
    }
}