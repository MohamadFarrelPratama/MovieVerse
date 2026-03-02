package com.example.movieverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieVerseApp()
        }
    }
}

data class Movie(
    val title: String,
    val year: String,
    val rating: String,
    val imageRes: Int,
    var isFavorite: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieVerseApp() {

    var movieList by remember {
        mutableStateOf(
            listOf(
                Movie("interstellar", "2014", "⭐ 4.9", R.drawable.interstellar),
                Movie("avengers_endgame", "2023", "⭐ 4.8", R.drawable.avengers_endgame),
                Movie("fastx", "2019", "⭐ 4.3", R.drawable.fastx)
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MovieVerse") }
            )
            Text(
                text = "By Mohamad Farrel Pratama | 2477051014",
                fontSize = 12.sp
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(movieList) { movie ->
                MovieItem(
                    movie = movie,
                    onFavoriteClick = {
                        movie.isFavorite = !movie.isFavorite
                    }
                )
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {

            Image(
                painter = painterResource(id = movie.imageRes),
                contentDescription = movie.title,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = movie.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Tahun: ${movie.year}")
                Text(text = "Rating: ${movie.rating}")
            }

            IconButton(onClick = { onFavoriteClick() }) {
                Icon(
                    imageVector = if (movie.isFavorite)
                        Icons.Default.Favorite
                    else
                        Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }
    }
}