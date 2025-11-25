package com.example.geoguesser.domain.model

data class AuthResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null
)
