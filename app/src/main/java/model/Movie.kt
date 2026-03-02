package com.example.movieverse.model

data class Movie(
    val title: String,
    val year: String,
    val rating: String,
    val imageRes: Int,
    var isFavorite: Boolean = false
)