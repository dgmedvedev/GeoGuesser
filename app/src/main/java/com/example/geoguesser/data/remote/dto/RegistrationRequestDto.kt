package com.example.geoguesser.data.remote.dto

data class RegistrationRequestDto(
    val username: String,
    val password: String,
    val confirmPassword: String,
    val email: String,
)
