package com.demo.places.domain.entity

data class Review(
    val authorName: String,
    val profilePicUrl: String,
    val rating: Int,
    val timeAgoText: String,
    val text: String
)