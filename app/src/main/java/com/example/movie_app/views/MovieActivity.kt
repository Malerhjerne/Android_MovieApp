package com.example.movie_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.movie_app.R

private const val api_url = "https://image.tmdb.org/t/p/w342/"

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_movie)


        val bundie = intent.getBundleExtra("bigbundle")

        val posterV = findViewById<ImageView>(R.id.movie_poster).apply {
            //val urlTocall = String.format(api_url + movieList[position].posterPath,)
            //Glide.with(viewHolder.imageView.context).clear(viewHolder.imageView)
            //Glide.with(viewHolder.imageView.context).load(urlTocall).into(viewHolder.imageView);
        }

    }
}