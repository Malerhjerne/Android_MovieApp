package com.example.movie_app.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "UserReview")
@Entity
data class UserReview(   /* By default, Room uses the class name as the database table name.  */

    //  @PrimaryKey(autoGenerate = true) var uid: Int = 1,
    @PrimaryKey() val uid:Int,
    @ColumnInfo(name= "userame") val userName:String?,
    @ColumnInfo(name="user_rating") val userRating:Int?,
    @ColumnInfo(name="user_comment") val userComment:String?



)