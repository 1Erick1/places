package com.demo.places.data.datasource.network.response

import com.google.gson.annotations.SerializedName

class NearbySearchResponse(
    @SerializedName("results") val results: List<PlaceResultResponse>
)