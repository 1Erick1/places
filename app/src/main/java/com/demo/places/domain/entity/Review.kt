package com.demo.places.domain.entity

data class Review(
    val placeId: String,
    val authorName: String,
    val profilePicUrl: String?,
    val rating: Double,
    val timeAgoDescription: String,
    val text: String
)