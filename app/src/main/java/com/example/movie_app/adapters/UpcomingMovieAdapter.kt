package com.example.movie_app.adapters

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app.MovieFragment
import com.example.movie_app.BlueFragment
import com.example.movie_app.OnItemClickListener
import com.example.movie_app.R
import com.example.movie_app.models.MovieModel
import com.example.movie_app.viewmodels.MovieViewModel
import com.example.movie_app.views.MainActivity
import com.google.gson.annotations.SerializedName


private const val api_url = "https://image.tmdb.org/t/p/w342/"
/*https://image.tmdb.org/t/p/w342/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg*/
class UpcomingMovieAdapter():RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingViewHolder>(){


      //  var movieList = ArrayList<MovieModel>()       //
        var movieViewModel = MovieViewModel()
        var movieList = movieViewModel.movieUpcomingArrayList
        val movFragment = MovieFragment()

        private var pageCounter:Int = 1;



        inner class UpcomingViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener,
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
                    Log.i("CHEKKKposList",movieList.size.toString())

                    Log.i("CHEKKKpos",position.toString())
                    Log.i("CHEKKKtitle",movieList[position].title)
                    Log.i("CHEKKKid",movieList[position].id.toString())

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




        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context).inflate(
                R.layout.movie_upcoming_recyclerview_layout,
                parent,
                false
            )

            return UpcomingViewHolder(layoutInflater);
        }

        override fun getItemCount(): Int {
            return movieList.size
        }

        override fun onBindViewHolder(viewHolder: UpcomingViewHolder, position: Int) {

            viewHolder.textView.text = movieList[position].title

            Log.i("MovieAdapter Number", position.toString())
            Log.i("Chek Glide", movieList[position].posterPath.toString())

            val urlTocall = String.format(api_url + movieList[position].posterPath)
            Glide.with(viewHolder.imageView.context).clear(viewHolder.imageView)
            Glide.with(viewHolder.imageView.context).load(urlTocall).into(viewHolder.imageView);


            Log.i("TESSSSSS", movieList.size.toString())
            Log.i("NYCHECK", position.toString())

            if (position == movieList.size -1){

                // pageCounter++;

            }

        }

        fun setData(){

            notifyDataSetChanged()
            Log.i("Tessss", "Are ve Here")
        }
    }
