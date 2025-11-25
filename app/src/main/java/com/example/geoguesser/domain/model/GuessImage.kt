package com.example.geoguesser.domain.model

data class GuessImage(
    val imageId: String,
    val description: String,
    val url: String,
    val isGuessed: Boolean,
    val attempts: Int,
    val location: Location,
)
