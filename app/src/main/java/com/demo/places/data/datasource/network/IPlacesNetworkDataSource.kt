package com.demo.places.data.datasource.network

import com.demo.places.domain.entity.PlaceDetail
import com.demo.places.domain.entity.PlaceResult

interface IPlacesNetworkDataSource {
    suspend fun searchPlaces(keyword: String, latitude: Double, longitude: Double, radius: Int): List<PlaceResult>

    suspend fun getPlaceDetail(id: String): PlaceDetail
}