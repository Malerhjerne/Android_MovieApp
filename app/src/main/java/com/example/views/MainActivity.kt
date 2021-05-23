package com.example.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_app.MovieAdapter
import com.example.movie_app.R
import com.example.movie_app.models.MovieModel
import com.example.movie_app.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {

   // private var movieViewModel = MovieViewModel()
    private var movieList = ArrayList<MovieModel>()
    private lateinit var adapter: MovieAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView

    private var movieViewModel = MovieViewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.loadMovies()

         recyclerView = findViewById(R.id.recyclerView)
         recyclerView.setHasFixedSize(false);

         layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

         recyclerView.layoutManager = layoutManager
         adapter = MovieAdapter(this)
         recyclerView.adapter = adapter

       // movieViewModel.getMovies().observe(this,{adapter.movieList})

        movieViewModel.getMovies().observe(this,{adapter.setData()})





        findViewById<Button>(R.id.testBtn).setOnClickListener({

        })


    }
    override fun onItemClick(position: Int) {
        Log.i("Interace Chek",position.toString())
        // Åbne Fragment -- ID

    }
}