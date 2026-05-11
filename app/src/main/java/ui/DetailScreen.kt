package com.example.movieverse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieverse.model.Movie
import coil.compose.AsyncImage

@Composable
fun DetailScreen(
    movie: Movie,
    onBackClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Box {
            AsyncImage(
                model = movie.imageUrl,
                contentDescription = movie.title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),

                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Tonton")
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = movie.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Tahun: ${movie.year}",
                color = Color.LightGray
            )

            Text(
                text = "⭐ ${movie.rating}",
                color = Color.Yellow
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = movie.desc,
                color = Color.White,
                lineHeight = 20.sp
            )
        }
    }
}