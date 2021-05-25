package com.example.movie_app.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.movie_app.api.MovieRetroCall
import com.example.movie_app.models.MovieModel
import kotlinx.coroutines.*


class MovieViewModel: ViewModel() {
    private val ApiKey: String = "203aa479a47e83d43ff03861e0f7f20e"
    private val pageNumber: Int = 1

    var moviePopularArrayList = ArrayList<MovieModel>();
    var movieUpcomingArrayList = ArrayList<MovieModel>()
    var movieTopRatedArrayList = ArrayList<MovieModel>()

    init {
        loadPopularMovies()
        loadUpcomingMovies()
        loadTopRatedMovies()
    }

    /*Region Popular Movies */

    fun loadPopularMovies(){
        // Do an asynchronous operation to fetch users
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MovieRetroCall.apiService.getPopularMovies(ApiKey, pageNumber)
                if (response.isSuccessful && response.body() != null) {
                    val response = response!!.body()
                    moviePopularArrayList.addAll(response!!.movies)

                } else {
                    Log.d("LOG", "Server error")
                }
            } catch (exception: Exception) {
                exception.message?.let {
                    Log.d("ERROR", it)
                }
            }
        }

    }

    /*END REGION - POPULAR MOVIES*/



    /*REGION Upcoming Movies*/

    fun loadUpcomingMovies() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MovieRetroCall.apiService.getUpcomingMovies(ApiKey,pageNumber)
                if(response.isSuccessful && response.body()!= null){
                    val response = response!!.body()
                    movieUpcomingArrayList.addAll(response!!.movies)

                } else {
                    Log.d("LOG", "Server error")
                }

            }catch (exception:Exception){
                exception.message?.let { Log.d("Error",it) }
            }
        }
    }




    /* END REGION UPCOMING MOVIES*/

    /* REGION Top Rated Movies */


    fun loadTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MovieRetroCall.apiService.getTopRatedMovies(ApiKey, pageNumber)
                if (response.isSuccessful && response.body() != null) {
                    val response = response!!.body()
                    movieTopRatedArrayList.addAll(response!!.movies)

                } else {
                    Log.d("LOG", "Server error")
                }
            } catch (exception: Exception) {
                exception.message?.let {
                    Log.d("ERROR", it)
                }
            }
        }
    }


    /* END REGION Top Rated Movies*/
}






