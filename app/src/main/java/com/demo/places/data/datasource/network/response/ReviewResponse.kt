package com.demo.places.data.datasource.network.response

import com.demo.places.domain.entity.Review
import com.google.gson.annotations.SerializedName

class ReviewResponse(
    @SerializedName("author_name") val authorName: String,
    @SerializedName("profile_photo_url") val profilePicUrl: String?,
    @SerializedName("rating") val rating: Int,
    @SerializedName("relative_time_description") val timeAgoDescription: String,
    @SerializedName("text") val text: String
) {
    fun toDomain(): Review{
        return Review(
            authorName = authorName,
            profilePicUrl = profilePicUrl,
            rating = rating,
            timeAgoDescription = timeAgoDescription,
            text = text
        )
    }
}