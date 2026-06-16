package com.example.movieverse.data.api

data class MovieResponse(
    val products: List<MovieItem>
)

data class MovieItem(
    val title: String,
    val thumbnail: String
)