package com.demo.places.domain.entity

class PlaceDetail(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val rating: Double?,
    val picture: String?,
    var reviews: List<Review>? = null,
    val iconUrl: String? = null,
    val iconBgColor: String?,
    var isFavorite: Boolean = false
)