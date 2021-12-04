package com.demo.places.data.datasource.network.response

import com.demo.places.domain.entity.PlaceResult
import com.google.gson.annotations.SerializedName

class PlaceResultResponse(
    @SerializedName("place_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("geometry") val geometry: GeometryResponse?,
    @SerializedName("icon") val iconUrl: String,
    @SerializedName("icon_background_color") val iconBgColor: String
){
    fun toDomain(): PlaceResult{
        return PlaceResult(
            id = id,
            name = name,
            latitude = geometry?.location?.lat?:0.0,
            longitude = geometry?.location?.lon?:0.0,
            iconUrl = iconUrl,
            iconBgColor = iconBgColor
        )
    }
}