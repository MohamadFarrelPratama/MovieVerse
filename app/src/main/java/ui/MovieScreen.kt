package com.example.movieverse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieverse.model.MovieSource

@Composable
fun MovieScreen(
    isError: Boolean = false,
    onMovieClick: (Int) -> Unit,
    onAboutClick: () -> Unit
) {

    val movieList = MovieSource.movieList

    var showError by remember {
        mutableStateOf(isError)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedGenre by remember {
        mutableStateOf("Semua")
    }

    val filteredMovies = movieList.filter { movie ->

        val genreMatch =
            selectedGenre == "Semua" ||
                    movie.genre == selectedGenre

        val searchMatch =
            movie.title.contains(
                searchText,
                ignoreCase = true
            )

        genreMatch && searchMatch
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        Text(
            text = "🎬 MovieVerse",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onAboutClick
        ) {
            Text("Tentang Aplikasi")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                showError = !showError
            }
        ) {
            Text(
                if (showError)
                    "Kembali Online"
                else
                    "Test Offline/Error"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (showError) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "❌ Gagal Memuat Data",
                        color = Color.Red,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Pastikan koneksi internet Anda menyala",
                        color = Color.LightGray
                    )
                }
            }

        } else {

            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                label = {
                    Text("Cari Film")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.horizontalScroll(
                    rememberScrollState()
                )
            ) {

                listOf(
                    "Semua",
                    "Action",
                    "Sci-Fi",
                    "Horror",
                    "Romance"
                ).forEach { genre ->

                    FilterChip(
                        selected = selectedGenre == genre,
                        onClick = {
                            selectedGenre = genre
                        },
                        label = {
                            Text(genre)
                        }
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Rekomendasi",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.horizontalScroll(
                    rememberScrollState()
                )
            ) {

                filteredMovies.forEach { movie ->
                    MovieCardHorizontal(movie)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Daftar Film",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {

                items(filteredMovies) { movie ->

                    MovieCardVertical(
                        movie = movie,
                        onClick = {

                            val originalIndex =
                                MovieSource.movieList.indexOf(movie)

                            onMovieClick(originalIndex)
                        }
                    )
                }
            }
        }
    }
}