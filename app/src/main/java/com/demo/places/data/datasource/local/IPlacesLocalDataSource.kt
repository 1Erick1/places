package com.demo.places.data.datasource.local

import com.demo.places.domain.entity.PlaceDetail

interface IPlacesLocalDataSource {
    suspend fun getPlaceDetail(id: String): PlaceDetail?
    suspend fun getAllPlaces(): List<PlaceDetail>
    suspend fun savePlace(placeDetail: PlaceDetail)
    suspend fun removePlace(placeId: String)
}