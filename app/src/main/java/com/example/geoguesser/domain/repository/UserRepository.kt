package com.example.geoguesser.domain.repository

import com.example.geoguesser.domain.model.Outcome
import com.example.geoguesser.domain.model.User

interface UserRepository {
    suspend fun getAllUsers(): Outcome<List<User>>
    suspend fun getUserById(userId: String): Outcome<User>
}
