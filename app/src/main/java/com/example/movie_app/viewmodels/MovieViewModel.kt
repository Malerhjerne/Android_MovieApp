package com.example.movie_app.viewmodels

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.api.MovieRetroCall
import com.example.movie_app.models.MovieModel
import kotlinx.coroutines.*


class MovieViewModel: ViewModel() {
    private val ApiKey: String = "203aa479a47e83d43ff03861e0f7f20e"
    private val pageNumber: Int = 2

    var moviePopularRecyclerListData: MutableLiveData<ArrayList<MovieModel>>
    var movieUpcomingRecyclerListData: MutableLiveData<ArrayList<MovieModel>>
    var movieTopRatedRecyclerListData : MutableLiveData<ArrayList<MovieModel>>
    init {
        moviePopularRecyclerListData = MutableLiveData<ArrayList<MovieModel>>()
        movieUpcomingRecyclerListData = MutableLiveData<ArrayList<MovieModel>>()
        movieTopRatedRecyclerListData= MutableLiveData<ArrayList<MovieModel>>()
    }

    /*Region Popular Movies */
    fun getPopularMovies(): MutableLiveData<ArrayList<MovieModel>> {
        return moviePopularRecyclerListData
    }


    fun loadPopularMovies() { // make it take a parameter to load specific site or make an counter that will increase the page num

        // Asynchronous operation to fetch users
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope
            try {
                val response = MovieRetroCall.apiService.getPopularMovies(ApiKey, 1)
                if (response.isSuccessful && response.body() != null) {
                    Log.i("test", response.body().toString())
                    val response = response.body()
                    moviePopularRecyclerListData.postValue(response!!.movies)
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

    fun loadPopularMoviesFromPopularMovieAdapter(copyList: ArrayList<MovieModel>, page: Int) {
        // Do an asynchronous operation to fetch users
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MovieRetroCall.apiService.getPopularMovies(ApiKey, page)
                if (response.isSuccessful && response.body() != null) {
                    Log.i("test", response.body().toString())
                    val response = response!!.body()
                    moviePopularRecyclerListData.postValue(response!!.movies)

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

    }/*END REGION - POPULAR MOVIES*/

    /*REGION Upcoming Movies*/
    fun getUpcomingMovies(): MutableLiveData<ArrayList<MovieModel>> {
        return movieUpcomingRecyclerListData
    }

    fun loadUpcomingMovies(copyList: ArrayList<MovieModel>) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MovieRetroCall.apiService.getTopRatedMovies(ApiKey,pageNumber)
                if(response.isSuccessful && response.body()!= null){
                    val response = response!!.body()
                    movieUpcomingRecyclerListData.postValue(response!!.movies)
                    copyList.addAll(response.movies)

                    for(ele in copyList){
                        Log.i("Testing",ele.title)
                    }
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

    /* END REGION Top Rated Movies*/

    fun getTopRatedMovies():MutableLiveData<ArrayList<MovieModel>>{
        return movieTopRatedRecyclerListData
    }

    fun loadTopRatedMovies(copyList: ArrayList<MovieModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MovieRetroCall.apiService.getPopularMovies(ApiKey, pageNumber)
                if (response.isSuccessful && response.body() != null) {
                    Log.i("test", response.body().toString())
                    val response = response!!.body()
                    moviePopularRecyclerListData.postValue(response!!.movies)

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






