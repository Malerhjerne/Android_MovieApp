package com.example.movie_app.api


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiRequest {
    @GET("movie/popular")
 suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "203aa479a47e83d43ff03861e0f7f20e",
        @Query("page") page: Int
    ):Response<MoviesResponse>
}