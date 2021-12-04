package com.demo.places.data.datasource.network.response

import com.google.gson.annotations.SerializedName

class GetDetailResponse(
    @SerializedName("result") val detail: PlaceDetailResponse
) {
}