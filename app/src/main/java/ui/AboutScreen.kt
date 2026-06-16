package com.example.movieverse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen(
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {

        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "🎬 MovieVerse",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Aplikasi katalog film yang dibuat menggunakan Jetpack Compose.",
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Fitur:",
            color = Color.Yellow,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = """
• Navigation Compose
• LazyColumn
• State Management
• Search Film
• Filter Genre
• Coil Image Loading
• Detail Screen
• Error Screen
            """.trimIndent(),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Developer:",
            color = Color.Yellow,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Mohamad Farrel Pratama",
            color = Color.White
        )

        Text(
            text = "UAP Pemrograman Mobile 2026",
            color = Color.White
        )
    }
}