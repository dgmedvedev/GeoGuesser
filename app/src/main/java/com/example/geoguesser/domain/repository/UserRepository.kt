package com.example.geoguesser.domain.repository

import com.example.geoguesser.domain.model.User

interface UserRepository {
    suspend fun getAllUsers(): Result<List<User>>
    suspend fun getUserById(userId: String): Result<User>
}
