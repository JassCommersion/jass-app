package com.example.jass_app.data

sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val e: Exception): Result<T>()
}