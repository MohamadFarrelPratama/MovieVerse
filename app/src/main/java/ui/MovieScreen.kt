package com.example.movieverse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import kotlinx.coroutines.delay

@Composable
fun MovieScreen(
    isError: Boolean = false,
    onMovieClick: (Int) -> Unit
){
    val movieList = MovieSource.movieList

    // simulasi error koneksi
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        // ================= HEADER =================

        Text(
            text = "🎬 MovieVerse",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ================= BUTTON TEST ERROR =================

        Button(
            onClick = {
                isError = !isError
            }
        ) {
            Text(
                if (isError)
                    "Kembali Online"
                else
                    "Test Offline/Error"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ================= ERROR SCREEN =================

        if (isError) {

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

            // ================= REKOMENDASI =================

            Text(
                text = "Rekomendasi",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                movieList.forEach { movie ->
                    MovieCardHorizontal(movie)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ================= DAFTAR FILM =================

            Text(
                text = "Daftar Film",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {

                itemsIndexed(movieList) { index, movie ->

                    MovieCardVertical(
                        movie = movie,
                        onClick = {
                            onMovieClick(index)
                        }
                    )
                }
            }
        }
    }
}