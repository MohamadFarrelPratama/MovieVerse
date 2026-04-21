package com.example.movieverse.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.movieverse.model.MovieSource

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            MovieScreen(
                onMovieClick = { index ->
                    navController.navigate("detail/$index")
                }
            )
        }

        composable("detail/{index}") { backStackEntry ->
            val index = backStackEntry.arguments
                ?.getString("index")
                ?.toIntOrNull() ?: 0

            val movie = MovieSource.movieList[index]

            DetailScreen(
                movie = movie,
                {
                    navController.popBackStack()
                }

            )
        }
    }
}