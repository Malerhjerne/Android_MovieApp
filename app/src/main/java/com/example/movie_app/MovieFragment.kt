package com.example.movie_app

import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MovieFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

       var movID = arguments?.getLong("movID")
        /*
        bundle.putString("movTitle", movieList[position].title)
        bundle.putString("movOverview", movieList[position].overview)
        bundle.putString("movPosterPath", movieList[position].posterPath)
        bundle.putString("movBackdrop", movieList[position].backdropPath)
        bundle.putFloat("movAvgRating", movieList[position].rating)
        bundle.putString("movReleaseDate", movieList[position].releaseDate)
        */

        Log.i("CHEK FRAGMENT", movID.toString())



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_movie, container, false)
        val textView = v.findViewById<TextView>(R.id.movie_title)







        return v
    }


}