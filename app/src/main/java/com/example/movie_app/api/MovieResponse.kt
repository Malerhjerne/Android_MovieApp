package com.example.movie_app.api

import com.example.movie_app.models.MovieModel
import com.google.gson.annotations.SerializedName

data class MoviesResponse (
        @SerializedName("page") val page: Int,
        @SerializedName("results") val movies: ArrayList<MovieModel>,
        @SerializedName("total_pages") val pages: Int
)
