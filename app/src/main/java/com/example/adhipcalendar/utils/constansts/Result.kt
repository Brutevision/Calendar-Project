package com.example.adhipcalendar.utils.constansts

sealed class Result<out T> {
    data class Success<out T>(val data: T? = null, val status: String? = null): Result<T>()
    data class Failed(val status: String? = null, val message: String): Result<Nothing>()
    data class Exception(val exception: kotlin.Exception? = null): Result<Nothing>()
    data class Loading(val isLoading: Boolean) : Result<Nothing>()
}