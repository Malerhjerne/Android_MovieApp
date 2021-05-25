package com.example.movie_app.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movie_app.R
import com.example.movie_app.database.MovieAppDatabase
import com.example.movie_app.models.UserReview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class MovieReviewFragment: Fragment(R.layout.movie_review) {
    private lateinit var movieReviewDB : MovieAppDatabase
    private lateinit var userReview: UserReview
    private var selectedBtn = 0
    private var movImage :String? =""


    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    movImage = arguments?.getString("movieimage").toString()

    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.movie_review, container, false)
        v.findViewById<Button>(R.id.sendReview).setOnClickListener{updateReviews()}

        var imageView = v.findViewById<ImageView>(R.id.movieReviewImage)
        val moviePosterURL = _PosterUrlBase + movImage
        Glide.with(imageView.context).clear(imageView)
        Glide.with(imageView.context).load(moviePosterURL).into(imageView)

        CoroutineScope(IO).launch {

            movieReviewDB = MovieAppDatabase.getDatabase(v.context)!!
            if(movieReviewDB.userReviewDao().countReviews() == 0){

                userReview = UserReview(
                    uid = 1,
                    userName = "TestPerson",
                    userRating = 3,
                    userComment = "SomeComment"

                )
                movieReviewDB.userReviewDao().insertAll(userReview)
            }
        }

        return v
    }

    private fun updateReviews() {

        val v = view

        val userRatingGroup = v?.findViewById<RadioGroup>(R.id.RatingGroup)

        CoroutineScope(IO).launch {

            val userName= v?.findViewById<EditText>(R.id.EditUsername)?.text.toString()

            if (userRatingGroup?.checkedRadioButtonId != -1) {
                selectedBtn = userRatingGroup?.checkedRadioButtonId!!
                val ratingAsText = v?.findViewById<RadioButton>(selectedBtn)?.text.toString()
                val ratingAsInt = ratingAsText.toInt()

                val userComment = v?.findViewById<EditText>(R.id.editComment)?.text.toString()
                val updatedReview = UserReview(
                    uid = movieReviewDB.userReviewDao().countReviews() + 1,
                    userName = userName,
                    userRating = ratingAsInt,
                    userComment = userComment
                )
                movieReviewDB.userReviewDao().insertAll(updatedReview)
            }
        }
        if(userRatingGroup?.checkedRadioButtonId == -1){
            val text = "You need to give the movie a Rating!"
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(v?.context, text, duration)
            toast.show()
        }else{
            val text = "You Have Submitted Your Review!"
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(v?.context, text, duration)
            toast.show()

            this.activity?.supportFragmentManager?.popBackStackImmediate()
        }
    }
}
