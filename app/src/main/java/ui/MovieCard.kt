package com.example.movieverse.ui

import androidx.compose.foundation.layout.*
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
import com.example.movieverse.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import coil.compose.AsyncImage
import androidx.compose.foundation.clickable

@Composable
fun MovieCardVertical(
    movie: Movie,
    onClick: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = onClick
        ) {
            Column {

                Box {
                    AsyncImage(
                        model = movie.imageUrl,
                        contentDescription = movie.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        contentScale = ContentScale.Crop
                    )

                    Button(
                        onClick = {
                            scope.launch {
                                isLoading = true
                                delay(1500)
                                snackbarHostState.showSnackbar(
                                    "🎬 ${movie.title} siap ditonton!"
                                )
                                isLoading = false
                            }
                        },
                        enabled = !isLoading,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                color = Color.White
                            )
                        } else {
                            Icon(Icons.Default.PlayArrow, null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Tonton")
                        }
                    }
                }

                Column(modifier = Modifier.padding(12.dp)) {
                    Text(movie.title, fontWeight = FontWeight.Bold)
                    Text("Tahun: ${movie.year}")
                    Text("⭐ ${movie.rating}")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(movie.desc, maxLines = 2)
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
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
            AsyncImage(
                model = movie.imageUrl,
                contentDescription = movie.title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),

                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(movie.title, fontWeight = FontWeight.Bold)
                Text("⭐ ${movie.rating}")
            }
        }
    }
}