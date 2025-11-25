package com.example.geoguesser.domain.repository

import com.example.geoguesser.domain.model.AuthResult

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<AuthResult>
    suspend fun logout(): Result<Unit>
    suspend fun isUserLoggedIn(): Boolean
}
