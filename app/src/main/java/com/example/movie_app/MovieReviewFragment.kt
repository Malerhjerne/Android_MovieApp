package com.example.movie_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.movie_app.database.MovieAppDatabase
import com.example.movie_app.models.UserReview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class MovieReviewFragment: Fragment(R.layout.movie_review) {
    private lateinit var movieReviewDB : MovieAppDatabase
    private lateinit var userReview: UserReview
    private var selectedBtn = 0;

    private lateinit var userName:EditText
    private lateinit var rating:RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

   //     var movID = arguments?.getLong("movID")
   //     Log.i("CHEK FRAGMENT", movID.toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.movie_review, container, false)

        v.findViewById<Button>(R.id.showLog).setOnClickListener { showLog() }
        //  val reviews : Flow<List<UserReview>> = movieReviewDB.userReview().getAll()
        v.findViewById<Button>(R.id.sendReview).setOnClickListener{updateReviews()}

        return v
    }

    private fun updateReviews() {
        CoroutineScope(IO).launch {

            val v = view

            val userName= v?.findViewById<EditText>(R.id.EditUsername)?.text.toString()
            val userRatingGroup = v?.findViewById<RadioGroup>(R.id.RatingGroup)

            if (userRatingGroup != null) {
                selectedBtn = userRatingGroup.checkedRadioButtonId
            }

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

    public fun showLog(){
        CoroutineScope(Dispatchers.IO).launch {
            val reviewDao = movieReviewDB.userReviewDao()
            val reviews :List<UserReview> = reviewDao.getAll()

            for(i in reviews)
            {
                Log.i("Database Test",i.userName.toString())
                Log.i("Database test",i.userRating.toString())

            }
        }
    }

}
