package com.demo.places.domain.entity

class PlaceDetail(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val rating: Int?,
    val pictures: List<String>?,
    val reviews: List<Review>?,
    var isFavorite: Boolean
)