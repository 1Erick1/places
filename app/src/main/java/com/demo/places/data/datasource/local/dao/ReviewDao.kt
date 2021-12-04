package com.demo.places.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.demo.places.data.datasource.local.dto.ReviewDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * from review WHERE placeId=:placeId")
    suspend fun getReviewsByPlace(placeId: String): List<ReviewDto>?

    @Insert
    suspend fun insertReviews(reviews: List<ReviewDto>)

    @Query("DELETE FROM review WHERE placeId=:placeId")
    suspend fun deleteReviewsByPlace(placeId: String)
}