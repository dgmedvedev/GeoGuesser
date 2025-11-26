package com.example.geoguesser.domain.model

data class Group(
    val groupId: String,
    val title: String,
    val images: List<GuessImage>
)
