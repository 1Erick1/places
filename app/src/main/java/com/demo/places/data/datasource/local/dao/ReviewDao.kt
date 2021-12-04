package com.demo.places.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.demo.places.data.datasource.local.dto.ReviewDto

@Dao
interface ReviewDao {
    @Query("SELECT * from review WHERE placeId=:placeId")
    suspend fun getReviewsByPlace(placeId: String): List<ReviewDto>
}