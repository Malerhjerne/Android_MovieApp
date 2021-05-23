package com.example.movie_app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRetroCall {


        private const val api_url = "https://api.themoviedb.org/3/"

        private fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(api_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService: MovieApiRequest = provideRetrofit().create(MovieApiRequest::class.java)
        }


