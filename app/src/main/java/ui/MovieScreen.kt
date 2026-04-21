package com.example.movieverse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieverse.model.MovieSource

@Composable
fun MovieScreen(onMovieClick: (Int) -> Unit) {

    val movieList = MovieSource.movieList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Text(
            text = "🎬 MovieVerse",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Rekomendasi",
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            movieList.forEach { movie ->
                MovieCardHorizontal(movie) // ✅ sekarang sudah dikenali
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Daftar Film",
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            itemsIndexed(movieList) { index, movie ->
                MovieCardVertical(
                    movie = movie,
                    onClick = { onMovieClick(index) }
                )
            }
        }
    }
}