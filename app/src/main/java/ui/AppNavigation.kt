package com.example.movieverse.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieverse.model.MovieSource

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        // ================= HOME SCREEN =================

        composable("home") {

            MovieScreen(

                // true = tampil error screen
                // false = tampil normal
                isError = false,

                onMovieClick = { index ->

                    navController.navigate("detail/$index")
                }
            )
        }

        // ================= DETAIL SCREEN =================

        composable("detail/{index}") { backStackEntry ->

            val index = backStackEntry.arguments
                ?.getString("index")
                ?.toIntOrNull() ?: 0

            val movie = MovieSource.movieList[index]

            DetailScreen(
                movie = movie,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}