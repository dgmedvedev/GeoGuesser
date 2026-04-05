package com.example.geoguesser.domain.model

sealed class Outcome<out T> {
    data class Success<out T>(val value: T) : Outcome<T>()
    data class Failure(val error: AppError) : Outcome<Nothing>()
}

sealed class AppError {
    object Unauthorized : AppError()
    data class InvalidResponse(val reason: String) : AppError()
    data class Server(val code: Int, val message: String?) : AppError()
    data class Network(val message: String?) : AppError()
    data class Validation(val message: String) : AppError()
    data class Unknown(val message: String? = null) : AppError()
}
