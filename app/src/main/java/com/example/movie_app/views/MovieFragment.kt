package com.example.movie_app.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlinx.coroutines.InternalCoroutinesApi
import com.bumptech.glide.Glide
import com.example.movie_app.R


const val _PosterUrlBase = "https://image.tmdb.org/t/p/w342"

class MovieFragment : Fragment() {


    private var _movID: Long? = 0
    private var _movTitle: String? = ""
    private var _movOverview: String? = ""
    private var _movPosterpath: String? = ""
    private var _movAvgRating: Float? = 0F
    private var _movReleaseDate: String? = ""
    private var movieImage:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _movID = arguments?.getLong("movID")
        _movTitle = arguments?.getString("movTitle")
        _movOverview = arguments?.getString("movOverview")
        _movPosterpath = arguments?.getString("movPosterPath")
        _movAvgRating = arguments?.getFloat("movAvgRating")
        _movReleaseDate = arguments?.getString("movReleaseDate")

        movieImage = _movPosterpath

    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_movie, container, false)
        val databaseBtn = v.findViewById<Button>(R.id.databaseBtn)
        databaseBtn.setOnClickListener { openMovieReviewFragment()}

        v.findViewById<TextView>(R.id.movie_title).text = _movTitle.toString()
        v.findViewById<TextView>(R.id.movie_overview).text = _movOverview.toString()
        v.findViewById<TextView>(R.id.movie_rating).text = _movAvgRating.toString()
        v.findViewById<TextView>(R.id.movie_release_date).text = _movReleaseDate.toString()


        val imageView = v.findViewById<ImageView>(R.id.movie_poster)

        val moviePosterURL = _PosterUrlBase + _movPosterpath.toString()

        Glide.with(imageView.context).clear(imageView)

        Glide.with(imageView.context).load(moviePosterURL).into(imageView)

        return v
    }

    @InternalCoroutinesApi
     fun openMovieReviewFragment(){
         val movieReviewFragment = MovieReviewFragment()
         var bundle = Bundle()

         bundle.putString("movieimage",movieImage)
         movieReviewFragment.setArguments(bundle)

         this.activity?.supportFragmentManager?.commit {
             setReorderingAllowed(true)
             replace(R.id.fragment,movieReviewFragment)
             addToBackStack("UpcomingFragment")}
     }
}