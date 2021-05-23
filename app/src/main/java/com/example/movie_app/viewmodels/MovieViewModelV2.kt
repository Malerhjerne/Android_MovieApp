package com.example.movie_app.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.api.MovieRetroCall
import com.example.movie_app.models.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModelV2: ViewModel() {
    private val ApiKey:String = "203aa479a47e83d43ff03861e0f7f20e"
    val movieList = MutableLiveData<List<MovieModel>>()


    private fun fetchData(){
        // Asynchronous operation to fetch users
        viewModelScope.launch(Dispatchers.IO) {viewModelScope
            try {
                val response = MovieRetroCall.apiService.getPopularMovies(ApiKey, 1)
                if (response.isSuccessful && response.body() != null) {
                    Log.i("test", response.body().toString())
                    val response = response.body()
                            movieList.postValue(response!!.movies)

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
private fun test(){
}
}