package com.example.movie_app.database
import androidx.room.*
import com.example.movie_app.models.UserReview


@Dao
interface UserReviewDao {
    //  @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    suspend fun insertAll(vararg users: UserReview)             // Inserting Object - Can have more than one Parameter

    @Delete
    suspend fun delete(user: UserReview)                           // Convenience methods that let you insert, update, and delete rows in your database without writing any SQL code.

    @Query("DELETE FROM UserReview")
    suspend fun deleteAll()

    @Query("Select * From UserReview")                  // Query methods that let you write your own SQL query to interact with the database.
    suspend fun getAll(): List<UserReview>

    @Update
    suspend fun update(user: UserReview): Int

    @Query("SELECT COUNT(*) from UserReview")
    suspend fun countReviews(): Int
/*
 Parameter Exampel
    @Query("SELECT * FROM user WHERE age > :minAge")
    fun loadAllUsersOlderThan(minAge: Int): Array<User>
   */
}

/* Documentation
* https://developer.android.com/training/data-storage/room/accessing-data#kotlin
*/