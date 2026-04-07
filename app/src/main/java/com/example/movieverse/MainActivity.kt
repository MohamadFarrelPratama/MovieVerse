package com.example.movieverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieverse.model.Movie
import com.example.movieverse.model.MovieSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MovieVerseApp() }
    }
}

@Composable
fun MovieVerseApp() {

    val movieList = MovieSource.movieList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {

        Text(
            text = "🎬 MovieVerse",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Rekomendasi",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            movieList.forEach { movie ->
                MovieCardHorizontal(movie)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Daftar Film",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(movieList) { movie ->
                MovieCardVertical(movie)
            }
        }
    }
}

@Composable
fun MovieCardHorizontal(movie: Movie) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .padding(end = 12.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Image(
                painter = painterResource(movie.imageRes),
                contentDescription = movie.title,
                modifier = Modifier.height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(movie.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("⭐ ${movie.rating}", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun MovieCardVertical(movie: Movie) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column {
            Column(modifier = Modifier.padding(12.dp)) {

                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Text(
                    text = "Tahun: ${movie.year}",
                    fontSize = 12.sp
                )

                Text(
                    text = "⭐ ${movie.rating}",
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = movie.desc,
                    fontSize = 13.sp,
                    color = Color.DarkGray,
                    maxLines = 3
                )
            }

            Box {
                Image(
                    painter = painterResource(movie.imageRes),
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                    Text("Tonton")
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {
                Text(movie.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Tahun: ${movie.year}", fontSize = 12.sp)
                Text("⭐ ${movie.rating}", fontSize = 12.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(movie.desc, fontSize = 12.sp, maxLines = 2)
            }
        }
    }
}
