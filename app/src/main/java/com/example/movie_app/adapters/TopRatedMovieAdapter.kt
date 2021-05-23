package com.example.movie_app.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app.R
import com.example.movie_app.models.MovieModel
import com.example.movie_app.viewmodels.MovieViewModel
import com.example.movie_app.views.MainActivity

private const val api_url = "https://image.tmdb.org/t/p/w342/"
class TopRatedMovieAdapter(private val clickListener: MainActivity):RecyclerView.Adapter<TopRatedMovieAdapter.TopRatedViewHolder>(){

        var movieList = ArrayList<MovieModel>()
        var movieViewModel = MovieViewModel()
        private var pageCounter:Int = 1;

        init {
            movieViewModel.loadTopRatedMovies(movieList)
            Log.i("Teess","Er Vi her ")

        }

        inner class TopRatedViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
            val textView: TextView
            val imageView: ImageView

            init {
                // Define click listener for the ViewHolder's View.
                textView = view.findViewById(R.id.titleTextView2)
                imageView = view.findViewById(R.id.movieImageView)
                view.setOnClickListener(this)
            }
            override fun onClick(v: View?) {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(position);
                }
            }
        }

        interface OnItemClickListener{
            fun onItemClick(position: Int);
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.movie_toprated_recyclerview_layout,parent,false)

            return TopRatedViewHolder(layoutInflater);
        }

        override fun getItemCount(): Int {
            return movieList.size
        }

        override fun onBindViewHolder(viewHolder: TopRatedViewHolder, position: Int) {

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
