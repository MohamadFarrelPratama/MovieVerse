package com.example.movieverse.data.repository

import com.example.movieverse.data.api.ApiService

class MovieRepository(
    private val apiService: ApiService
) {

    suspend fun getMovies() =
        apiService.getMovies()
}