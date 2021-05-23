package com.example.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.movie_app.PopularMovieAdapter
import com.example.movie_app.R
import com.example.movie_app.models.MovieModel
import com.example.movie_app.viewmodels.MovieViewModel
import com.example.movie_app.viewmodels.UpcomingMovieAdapter

class MainActivity : AppCompatActivity(), PopularMovieAdapter.OnItemClickListener,UpcomingMovieAdapter.OnItemClickListener {

   // private var movieViewModel = MovieViewModel()
   private var upcomingmovieList = ArrayList<MovieModel>()
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    private var movieViewModel = MovieViewModel()

    private var movieViewModel_upcomingMovie = MovieViewModel()
    private lateinit var upcomingMovieAdapter: UpcomingMovieAdapter
    private lateinit var recyclerViewUpcomingMovie: RecyclerView
    private lateinit var layoutManagerUpcoming: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val View = this.window.decorView
        val backgroundColor = View.resources.getColor(R.color.blackishBackground)
        View.setBackgroundColor(backgroundColor)



        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.loadPopularMovies()
        movieViewModel.loadUpcomingMovies(upcomingmovieList)




        recyclerView = findViewById(R.id.recyclerViewPopular)
        recyclerView.setHasFixedSize(false);


        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        recyclerView.layoutManager = layoutManager
        popularMovieAdapter = PopularMovieAdapter(this)
        recyclerView.adapter = popularMovieAdapter

       /**/

        recyclerViewUpcomingMovie = findViewById(R.id.recyclerviewUpComing)
        recyclerViewUpcomingMovie.setHasFixedSize(false)
        layoutManagerUpcoming = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewUpcomingMovie.layoutManager = layoutManagerUpcoming


        upcomingMovieAdapter = UpcomingMovieAdapter(this)
        recyclerViewUpcomingMovie.adapter = upcomingMovieAdapter




        /*Observing */
        movieViewModel.getPopularMovies().observe(this,{popularMovieAdapter.setData()})

        movieViewModel.getUpcomingMovies().observe(this,{upcomingMovieAdapter.setData()})
        // movieViewModel.getMovies().observe(this,{adapter.movieList})





        findViewById<Button>(R.id.testBtn).setOnClickListener({

        })


    }
    override fun onItemClick(position: Int) {
        Log.i("Interace Chek",position.toString())
        // Åbne Fragment -- ID

    }
}