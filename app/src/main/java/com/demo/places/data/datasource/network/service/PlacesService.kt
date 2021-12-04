package com.demo.places.data.datasource.network.service

import com.demo.places.data.datasource.network.response.GetDetailResponse
import com.demo.places.data.datasource.network.response.NearbySearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesService {
    @GET("nearbysearch/json")
    suspend fun searchNearby(@Query("keyword") keyword:String, @Query("location") location: String, @Query("radius") radius: Int): NearbySearchResponse

    @GET("details/json")
    suspend fun getPlaceDetail(@Query("place_id") id: String): GetDetailResponse
}