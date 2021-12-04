package com.demo.places.data.datasource.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.places.domain.entity.Review

@Entity(tableName = "review")
data class ReviewDto(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val placeId: String,
    val authorName: String,
    val text: String,
    val rating: Double,
    val profilePicUrl: String?,
    val timeAgoDescription: String
){
    fun toDomain() = Review(
        placeId = placeId,
        authorName = authorName,
        profilePicUrl = profilePicUrl,
        rating = rating,
        timeAgoDescription = timeAgoDescription,
        text = text
    )

    companion object{
        fun fromDomain(review: Review) = ReviewDto(
            placeId = review.placeId,
            authorName = review.authorName,
            text = review.text,
            rating = review.rating,
            profilePicUrl = review.profilePicUrl,
            timeAgoDescription = review.timeAgoDescription
        )
    }
}