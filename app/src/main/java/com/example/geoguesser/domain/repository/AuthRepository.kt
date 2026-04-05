package com.example.geoguesser.domain.repository

import com.example.geoguesser.domain.model.Outcome

interface AuthRepository {
    suspend fun login(
        username: String,
        password: String
    ): Outcome<String>

    suspend fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): Outcome<String>

    suspend fun logout(): Outcome<Unit>
    suspend fun isUserLoggedIn(): Boolean
}
