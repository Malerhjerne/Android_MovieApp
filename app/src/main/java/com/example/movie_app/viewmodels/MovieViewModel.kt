package com.example.movie_app.viewmodels

import android.graphics.Movie
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

    var moviePopularRecyclerListData: MutableLiveData<ArrayList<MovieModel>> = MutableLiveData<ArrayList<MovieModel>>()
    var movieUpcomingRecyclerListData: MutableLiveData<ArrayList<MovieModel>> = MutableLiveData<ArrayList<MovieModel>>()
    var movieTopRatedRecyclerListData : MutableLiveData<ArrayList<MovieModel>> = MutableLiveData<ArrayList<MovieModel>>()

    init {
        loadPopularMovies(moviePopularArrayList)
        loadUpcomingMovies(movieUpcomingArrayList)
        loadTopRatedMovies(movieTopRatedArrayList)
    }

    /*Region Popular Movies */
    fun getPopularMovies(): MutableLiveData<ArrayList<MovieModel>> {
        return moviePopularRecyclerListData
    }

    fun loadPopularMovies(copyList: ArrayList<MovieModel>){  //
        // Do an asynchronous operation to fetch users
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MovieRetroCall.apiService.getPopularMovies(ApiKey, pageNumber)
                if (response.isSuccessful && response.body() != null) {
                    Log.i("Check responsebody popular", response.body().toString())
                    val response = response!!.body()
                    moviePopularRecyclerListData.postValue(response!!.movies)
                    //copyList.addAll(response.movies)
                    moviePopularArrayList.addAll(response.movies)

                } else {
                    Log.d("LOG", "Server error")
                }
            } catch (exception: Exception) {
                exception.message?.let {
                    Log.d("ERROR", it)
                }
            }
        }

    }/*END REGION - POPULAR MOVIES*/

    /*REGION Upcoming Movies*/
    fun getUpcomingMovies(): MutableLiveData<ArrayList<MovieModel>> {
        return movieUpcomingRecyclerListData
    }

    fun loadUpcomingMovies(copyList: ArrayList<MovieModel>) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MovieRetroCall.apiService.getUpcomingMovies(ApiKey,pageNumber)
                if(response.isSuccessful && response.body()!= null){
                    Log.i("Check responsebody upcoming ", response.body().toString())
                    val response = response!!.body()
                    movieUpcomingRecyclerListData.postValue(response!!.movies)
                    movieUpcomingArrayList.addAll(response.movies)
                   //copyList.addAll(response.movies)

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

    fun getTopRatedMovies():MutableLiveData<ArrayList<MovieModel>>{
        return movieTopRatedRecyclerListData
    }

    fun loadTopRatedMovies(copyList: ArrayList<MovieModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MovieRetroCall.apiService.getTopRatedMovies(ApiKey, pageNumber)
                if (response.isSuccessful && response.body() != null) {
                    Log.i("Check responsebody topRated", response.body().toString())
                    val response = response!!.body()
                    moviePopularRecyclerListData.postValue(response!!.movies)
                    movieTopRatedArrayList.addAll(response.movies)

                  //  copyList.addAll(response.movies)

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






