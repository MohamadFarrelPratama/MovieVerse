package com.example.movieverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import com.example.movieverse.model.Movie
import com.example.movieverse.model.MovieSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieVerseApp()
        }
    }
}

@Composable
fun MovieVerseApp() {

    val movieList = MovieSource.movieList
    var searchText by remember { mutableStateOf("") }

    val filteredList = movieList.filter {
        it.title.contains(searchText, ignoreCase = true)
    }

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Color.Red
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Film")
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                Column {

                    Text(
                        text = "🎬 MovieVerse",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("Cari film...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Rekomendasi",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(movieList) { movie ->
                            MovieRowItem(movie)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Daftar Film",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(filteredList) { movie ->
                MovieItem(movie)
            }
        }
    }
}

@Composable
fun MovieRowItem(movie: Movie) {

    Card(
        modifier = Modifier.width(140.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column {

            Image(
                painter = painterResource(id = movie.imageRes),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Text(
                    text = "⭐ ${movie.rating}",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {

    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column {

            Box {

                Image(
                    painter = painterResource(id = movie.imageRes),
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector =
                            if (isFavorite)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint =
                            if (isFavorite) Color.Red
                            else Color.White
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = movie.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Tahun: ${movie.year}",
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "⭐ ${movie.rating}")

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tonton Sekarang")
                }
            }
        }
    }
}