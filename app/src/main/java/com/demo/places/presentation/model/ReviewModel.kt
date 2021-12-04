package com.demo.places.presentation.model

import com.demo.places.domain.entity.Review

data class ReviewModel(
    val placeId: String,
    val authorName: String,
    val rating: Double,
    val text: String,
    val profilePicUrl: String?,
    val timeAgoDescription: String
) {
    companion object{
        fun fromDomain(review: Review) = ReviewModel(
            placeId = review.placeId,
            authorName = review.authorName,
            rating = review.rating,
            text = review.text,
            profilePicUrl = review.profilePicUrl,
            timeAgoDescription = review.timeAgoDescription
        )
    }
}