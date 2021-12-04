package com.demo.places.presentation.model

import com.demo.places.domain.entity.PlaceDetail
import com.google.android.gms.maps.model.LatLng

data class PlaceDetailModel(
    val id: String,
    val name: String,
    val location: LatLng,
    val address: String,
    val rating: Double?,
    val pictures: List<String>?,
    val reviews: List<ReviewModel>?,
    val markerColor: String?,
    val isFavorite: Boolean
) {
    companion object{
        fun fromDomain(place: PlaceDetail) = PlaceDetailModel(
            id = place.id,
            name = place.name,
            location = LatLng(place.latitude,place.longitude),
            address = place.address,
            rating = place.rating,
            pictures = place.pictures,
            reviews = place.reviews?.map { ReviewModel.fromDomain(it) },
            markerColor = place.iconBgColor,
            isFavorite = place.isFavorite
        )
    }

    fun toDomain() = PlaceDetail(
        id = id,
        name = name,
        latitude = location.latitude,
        longitude = location.longitude,
        address = address,
        rating = rating,
        pictures = pictures,
        reviews = reviews?.map { it.toDomain() },
        iconBgColor = markerColor
    )
}