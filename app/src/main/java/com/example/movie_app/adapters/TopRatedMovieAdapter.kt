package com.example.movie_app.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app.views.MovieFragment
import com.example.movie_app.R
import com.example.movie_app.viewmodels.MovieViewModel

private const val api_url = "https://image.tmdb.org/t/p/w342/"
class TopRatedMovieAdapter():RecyclerView.Adapter<TopRatedMovieAdapter.TopRatedViewHolder>()
    {

        var movieViewModel = MovieViewModel()
        var movieList = movieViewModel.movieTopRatedArrayList
        val movFragment = MovieFragment()

        inner class TopRatedViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener,
            OnItemClickListener {
            val textView: TextView
            val imageView: ImageView

            init {
                // Define click listener for the ViewHolder's View.
                textView = view.findViewById(R.id.titleTextView2)
                imageView = view.findViewById(R.id.movieImageView)
                view.setOnClickListener(this)
            }

            override fun onItemClick(position: Int) {
                var bundle = Bundle()
                bundle.putLong("movID", movieList[position].id)
                bundle.putString("movTitle", movieList[position].title)
                bundle.putString("movOverview", movieList[position].overview)
                bundle.putString("movPosterPath", movieList[position].posterPath)
                bundle.putString("movBackdrop", movieList[position].backdropPath)
                bundle.putFloat("movAvgRating", movieList[position].rating)
                bundle.putString("movReleaseDate", movieList[position].releaseDate)
                movFragment.setArguments(bundle)

            }

            override fun onClick(v: View?) {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION) {
                   onItemClick(position)

                    val activity = v!!.context as FragmentActivity
                    activity.supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.fragment,movFragment)
                        addToBackStack("UpcomingFragment")}

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.movie_recyclerview_layout,parent,false)

            return TopRatedViewHolder(layoutInflater)
        }

        override fun getItemCount(): Int {
            return movieList.size
        }

        override fun onBindViewHolder(viewHolder: TopRatedViewHolder, position: Int) {

            viewHolder.textView.text = movieList[position].title

            val urlTocall = String.format(api_url + movieList[position].posterPath,)
            Glide.with(viewHolder.imageView.context).clear(viewHolder.imageView)
            Glide.with(viewHolder.imageView.context).load(urlTocall).into(viewHolder.imageView)
        }
    }
