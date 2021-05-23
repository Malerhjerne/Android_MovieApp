package com.example.movie_app.api


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
const val APIKey = "203aa479a47e83d43ff03861e0f7f20e"
interface MovieApiRequest {
    @GET("movie/popular")
 suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = APIKey,
        @Query("page") page: Int
    ):Response<MoviesResponse>

   @GET("movie/upcoming")
   suspend fun getUpcomingMovies(
           @Query("api_key") apiKey:String = APIKey,
           @Query("page")page:Int
   ):Response<MoviesResponse>

   @GET("movie/top_rated")
   suspend fun getTopRatedMovies(
           @Query("api_key") apiKey: String= APIKey,
           @Query("page")page:Int
   ):Response<MoviesResponse>
}