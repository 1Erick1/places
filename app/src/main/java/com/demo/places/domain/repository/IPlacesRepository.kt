package com.demo.places.domain.repository

import com.demo.places.domain.entity.PlaceDetail
import com.demo.places.domain.entity.PlaceResult

interface IPlacesRepository {
    suspend fun searchPlaces(keyword: String, latitude: Double, longitude: Double, radius: Int): List<PlaceResult>

    suspend fun getPlaceDetail(id: String): PlaceDetail

    suspend fun setFavoriteState(isFavorite: Boolean)
}