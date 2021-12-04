package com.demo.places.data.datasource.network.response

import com.demo.places.BuildConfig
import com.google.gson.annotations.SerializedName

class PictureResponse(
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("photo_reference") val reference: String
) {
    fun getUrl(): String{
        return "${BuildConfig.PLACES_API_URL}photo?maxwidth=1080&photo_reference=$reference&key=${BuildConfig.GOOGLE_API_KEY}"
    }
}