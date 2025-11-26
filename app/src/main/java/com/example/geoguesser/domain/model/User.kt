package com.example.geoguesser.domain.model

data class User(
    val id: String,
    val name: String,
    val avatarUrl: String,
    val points: Int,
    val groups: List<Group>,
)
