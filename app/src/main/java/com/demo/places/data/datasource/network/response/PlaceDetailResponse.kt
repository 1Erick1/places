package com.demo.places.data.datasource.network.response

import com.demo.places.domain.entity.PlaceDetail
import com.google.gson.annotations.SerializedName

class PlaceDetailResponse(
    @SerializedName("place_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("icon") val iconUrl: String?,
    @SerializedName("icon_background_color") val iconBgColor: String?,
    @SerializedName("formatted_address") val address: String?,
    @SerializedName("geometry") val geometry: GeometryResponse?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("reviews") val reviews: List<ReviewResponse>?,
    @SerializedName("photos") val pictures: List<PictureResponse>?
) {
    fun toDomain(): PlaceDetail{
        return PlaceDetail(
            id = id,
            name = name,
            iconUrl = iconUrl,
            iconBgColor = iconBgColor,
            address = address?:"",
            latitude = geometry?.location?.lat?:0.0,
            longitude = geometry?.location?.lon?:0.0,
            rating = rating,
            reviews = reviews?.map { it.toDomain(id) },
            pictures = pictures?.map { it.getUrl() }
        )
    }
}