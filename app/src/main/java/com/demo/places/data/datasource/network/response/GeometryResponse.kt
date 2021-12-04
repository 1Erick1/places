package com.demo.places.data.datasource.network.response

import com.google.gson.annotations.SerializedName

class GeometryResponse(
    @SerializedName("location") val location: LocationResponse
){
    class LocationResponse(
        @SerializedName("lat") val lat: Double,
        @SerializedName("lon") val lon: Double
    )
}