package com.example.movie_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie_app.models.UserReview
import kotlinx.coroutines.InternalCoroutinesApi


@Database(entities = [UserReview::class],version = 1, exportSchema = false)
abstract class MovieAppDatabase : RoomDatabase() {

    abstract fun userReviewDao():UserReviewDao  // For each DAO class that is associated with the database, the database class must define an abstract method that has zero arguments and returns an instance of the DAO class

    @InternalCoroutinesApi
    companion object{
        // Singelton - only one database at same time
        @Volatile
        private var INSTANCE:MovieAppDatabase? =null


        fun getDatabase (context: Context):MovieAppDatabase?{
            //if the instance is not null - return it else create database
            if(INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MovieAppDatabase::class.java,
                    "UserReviews"
                ).build()
                // allowOnmainthred.build
            }
            return INSTANCE
        }

    }
}