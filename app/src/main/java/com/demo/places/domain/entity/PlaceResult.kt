package com.demo.places.domain.entity

data class PlaceResult(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val iconUrl: String?,
    val iconBgColor: String?
)