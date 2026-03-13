package com.example.movieverse.model

import com.example.movieverse.R

object MovieSource {

    val movieList = listOf(

        Movie(
            title = "Interstellar",
            year = "2014",
            rating = "4.9",
            imageRes = R.drawable.interstellar
        ),

        Movie(
            title = "Avengers Endgame",
            year = "2019",
            rating = "4.8",
            imageRes = R.drawable.avengers_endgame
        ),

        Movie(
            title = "Fast X",
            year = "2023",
            rating = "4.3",
            imageRes = R.drawable.fastx
        )

    )
}