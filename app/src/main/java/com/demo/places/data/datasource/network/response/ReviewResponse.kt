package com.demo.places.data.datasource.network.response

import com.demo.places.domain.entity.Review
import com.google.gson.annotations.SerializedName

class ReviewResponse(
    @SerializedName("author_name") val authorName: String,
    @SerializedName("profile_photo_url") val profilePicUrl: String?,
    @SerializedName("rating") val rating: Double,
    @SerializedName("relative_time_description") val timeAgoDescription: String,
    @SerializedName("text") val text: String
) {
    fun toDomain(placeId: String): Review{
        return Review(
            placeId = placeId,
            authorName = authorName,
            profilePicUrl = profilePicUrl,
            rating = rating,
            timeAgoDescription = timeAgoDescription,
            text = text
        )
    }
}