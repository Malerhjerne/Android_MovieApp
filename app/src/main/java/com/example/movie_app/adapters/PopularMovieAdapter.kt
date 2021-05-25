package com.example.movie_app.adapters

import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app.BlueFragment
import com.example.movie_app.MovieFragment
import com.example.movie_app.OnItemClickListener
import com.example.movie_app.R
import com.example.movie_app.models.MovieModel
import com.example.movie_app.viewmodels.MovieViewModel
import com.example.movie_app.views.MainActivity

private const val api_url = "https://image.tmdb.org/t/p/w342/"
/*https://image.tmdb.org/t/p/w342/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg*/
class PopularMovieAdapter() : RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    //var movieList = ArrayList<MovieModel>()       // used when calling api from adapter -- now api is called in viewmodel
    var movieViewModel = MovieViewModel()
    var movieList = movieViewModel.moviePopularArrayList
    private var pageCounter:Int = 1;
    val movFragment = MovieFragment()



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),OnItemClickListener {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define listener for the ViewHolder's View.
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
               onItemClick(position);
                val activity = v!!.context as FragmentActivity
                activity.supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.fragment,movFragment)
                    addToBackStack("UpcomingFragment")}

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.movie_recyclerview_layout,parent,false)

        return ViewHolder(layoutInflater);
    }

    override fun getItemCount(): Int {
       return movieList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        Log.i("Check OnBindViewHolder Position",position.toString())
        Log.i("Chek Glide",movieList[position].posterPath.toString())

        viewHolder.textView.text = movieList[position].title

        val urlTocall = String.format(api_url + movieList[position].posterPath,)
        Glide.with(viewHolder.imageView.context).clear(viewHolder.imageView)
        Glide.with(viewHolder.imageView.context).load(urlTocall).into(viewHolder.imageView);


        if (position == movieList.size -1){

           // pageCounter++;
        }
    }

    fun setData(){

        notifyDataSetChanged()
        Log.i("Check NotifyDataSetChanged","PopularMovie")
    }
}
