package com.example.movie_app.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app.OnItemClickListener
import com.example.movie_app.R
import com.example.movie_app.models.MovieModel
import com.example.movie_app.viewmodels.MovieViewModel

private const val api_url = "https://image.tmdb.org/t/p/w342/"
/*https://image.tmdb.org/t/p/w342/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg*/
class UpcomingMovieAdapter():RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingViewHolder>(){


        var movieList = ArrayList<MovieModel>()       //
        var movieViewModel = MovieViewModel()
        private var pageCounter:Int = 1;


        init {
            movieViewModel.loadUpcomingMovies(movieList)
            Log.i("Teess","Er Vi her ")

        }

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
                    Log.i("CHEKKK",position.toString())
                    Log.i("CHEKKK",movieList[position].title)
                }


            override fun onClick(v: View?) {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    onItemClick(position);
                }
            }
        }




        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.movie_upcoming_recyclerview_layout,parent,false)

            return UpcomingViewHolder(layoutInflater);
        }

        override fun getItemCount(): Int {
            return movieList.size
        }

        override fun onBindViewHolder(viewHolder: UpcomingViewHolder, position: Int) {

            viewHolder.textView.text = movieList[position].title

            Log.i("MovieAdapter Number",position.toString())
            Log.i("Chek Glide",movieList[position].posterPath.toString())

            val urlTocall = String.format(api_url + movieList[position].posterPath,)
            Glide.with(viewHolder.imageView.context).clear(viewHolder.imageView)
            Glide.with(viewHolder.imageView.context).load(urlTocall).into(viewHolder.imageView);


            Log.i("TESSSSSS",movieList.size.toString())
            Log.i("NYCHECK",position.toString())

            if (position == movieList.size -1){

                // pageCounter++;

            }

        }

        fun setData(){

            notifyDataSetChanged()
            Log.i("Tessss","Are ve Here")
        }
    }
