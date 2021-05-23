package com.example.movie_app.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.api.MovieRetroCall
import com.example.movie_app.models.MovieModel
import kotlinx.coroutines.*


class MovieViewModel: ViewModel(){
private val ApiKey:String = "203aa479a47e83d43ff03861e0f7f20e"
    private val pageNumber:Int = 2
    var movieRecylerListData:MutableLiveData<ArrayList<MovieModel>>

      init {
          movieRecylerListData = MutableLiveData<ArrayList<MovieModel>>()
      }

        fun getMovies(): MutableLiveData<ArrayList<MovieModel>> {
            return movieRecylerListData
        }

        fun loadMovies() { // make it take a parameter to load specific site or make an counter that will increase the page num

            // Asynchronous operation to fetch users
            viewModelScope.launch(Dispatchers.IO) {viewModelScope
                try {
                    val response = MovieRetroCall.apiService.getPopularMovies(ApiKey, 1)
                    if (response.isSuccessful && response.body() != null) {
                        Log.i("test", response.body().toString())
                        val response = response.body()
                       movieRecylerListData.postValue(response!!.movies)
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

    fun loadMoviesFromMovieAdapter(copyList:ArrayList<MovieModel>, page:Int) {
        // Do an asynchronous operation to fetch users
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val response = MovieRetroCall.apiService.getPopularMovies(ApiKey, page)
                if (response.isSuccessful && response.body() != null) {
                    Log.i("test", response.body().toString())
                    val response = response!!.body()
                    movieRecylerListData.postValue(response!!.movies)

                    /* Just for test */
                    copyList.addAll(response.movies)


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
  }







