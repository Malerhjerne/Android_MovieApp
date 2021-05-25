package com.example.movie_app.views

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.movie_app.MovieFragment
import com.example.movie_app.R
import com.example.movie_app.adapters.PopularMovieAdapter
import com.example.movie_app.adapters.TopRatedMovieAdapter
import com.example.movie_app.adapters.UpcomingMovieAdapter
import com.example.movie_app.database.MovieAppDatabase
import com.example.movie_app.models.MovieModel
import com.example.movie_app.models.UserReview
import com.example.movie_app.viewmodels.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){
    /*ViewModel */
    private var movieViewModel = MovieViewModel()

    /*Popular */
    private var popularMovieList = ArrayList<MovieModel>()
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var layoutManagerPopularMovies: RecyclerView.LayoutManager
    private lateinit var recyclerViewPopularMovies: RecyclerView

    /*Upcoming */
    private var upcomingMovieList = ArrayList<MovieModel>()
    private lateinit var upcomingMovieAdapter: UpcomingMovieAdapter
    private lateinit var recyclerViewUpcomingMovies: RecyclerView
    private lateinit var layoutManagerUpcoming: RecyclerView.LayoutManager

    /*Top Rated */
    private var topRatedMovieList= ArrayList<MovieModel>();
    private lateinit var topRatedMovieAdapter: TopRatedMovieAdapter
    private lateinit var recyclerViewTopRatedMovies: RecyclerView
    private lateinit var layoutManagerTopRated: RecyclerView.LayoutManager

    private lateinit var movieReviewDB : MovieAppDatabase
    private lateinit var userReview: UserReview


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularMovieAdapter = PopularMovieAdapter()
        upcomingMovieAdapter = UpcomingMovieAdapter()
        topRatedMovieAdapter = TopRatedMovieAdapter()


        setContentView(R.layout.activity_main)
        val view = this.window.decorView
        val backgroundColor = view.resources.getColor(R.color.blackishBackground)
        view.setBackgroundColor(backgroundColor)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        /* Popular*/
        recyclerViewPopularMovies = findViewById(R.id.recyclerViewPopular)
        recyclerViewPopularMovies.setHasFixedSize(false);

        layoutManagerPopularMovies = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularMovies.layoutManager = layoutManagerPopularMovies

        recyclerViewPopularMovies.adapter = popularMovieAdapter

        /* Upcoming*/
        recyclerViewUpcomingMovies = findViewById(R.id.recyclerviewUpComing)
        recyclerViewUpcomingMovies.setHasFixedSize(false)

        layoutManagerUpcoming = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewUpcomingMovies.layoutManager = layoutManagerUpcoming


        recyclerViewUpcomingMovies.adapter = upcomingMovieAdapter

        /*Top Rated */
        recyclerViewTopRatedMovies =findViewById(R.id.recyclerviewTopRated)
        recyclerViewTopRatedMovies.setHasFixedSize(false)

        layoutManagerTopRated = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewTopRatedMovies.layoutManager = layoutManagerTopRated


        recyclerViewTopRatedMovies.adapter = topRatedMovieAdapter

        /*Observing */
      //  movieViewModel.getPopularMovies().observe(this, { popularMovieAdapter.setData() })
       // movieViewModel.getUpcomingMovies().observe(this, { upcomingMovieAdapter.setData() })
       // movieViewModel.getTopRatedMovies().observe(this, { topRatedMovieAdapter.setData()})
        // movieViewModel.getMovies().observe(this,{adapter.movieList})

        /* Orientation Checker*/
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else {

        }
       /* findViewById<Button>(R.id.testBtn).setOnClickListener({
            Log.i("CHEK API",topRatedMovieList.size.toString())
            Log.i("CHEK API",upcomingMovieList.size.toString())
            Log.i("CHEK API",popularMovieList.size.toString())




        })*/
    }


    /*fun startfragment(bundle: Bundle){


        val intent = Intent(this, MovieActivity::class.java).apply{
            putExtra("bigbundle", bundle)
        }
        startActivity(intent)

        //findViewById<FragmentContainerView>(R.id.container)
        Log.i("BIGtttt", "BIG TESTTTTTT")

    }*/


    override fun onResume() {
        super.onResume()
        movieViewModel.loadTopRatedMovies(topRatedMovieList)
        movieViewModel.loadPopularMovies(popularMovieList)
        movieViewModel.loadUpcomingMovies(upcomingMovieList)

    }


}

