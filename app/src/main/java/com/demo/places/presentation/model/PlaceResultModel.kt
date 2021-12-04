package com.demo.places.presentation.model

import com.demo.places.domain.entity.PlaceResult
import com.google.android.gms.maps.model.LatLng

data class PlaceResultModel(
    val id: String,
    val name: String,
    val location: LatLng,
    val markerColor: String?
){
    companion object{
        fun fromDomain(place: PlaceResult) = PlaceResultModel(
            id = place.id,
            name = place.name,
            location = LatLng(place.latitude,place.longitude),
            markerColor = place.iconBgColor
        )
    }
}