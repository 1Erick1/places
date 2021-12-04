package com.demo.places.data.repository

import com.demo.places.data.datasource.local.IPlacesLocalDataSource
import com.demo.places.data.datasource.network.IPlacesNetworkDataSource
import com.demo.places.domain.entity.PlaceDetail
import com.demo.places.domain.entity.PlaceResult
import com.demo.places.domain.repository.IPlacesRepository

class PlacesRepository(
    private val placesNetworkDataSource: IPlacesNetworkDataSource,
    private val placesLocalDataSource: IPlacesLocalDataSource
): IPlacesRepository {

    override suspend fun searchPlaces(
        keyword: String,
        latitude: Double,
        longitude: Double,
        radius: Int
    ): List<PlaceResult> {
        return placesNetworkDataSource.searchPlaces(keyword, latitude, longitude, radius)
    }

    override suspend fun getPlaceDetail(id: String): PlaceDetail {
        val place = placesLocalDataSource.getPlaceDetail(id)
        return place?:placesNetworkDataSource.getPlaceDetail(id)
    }

    override suspend fun getFavoritePlaces(): List<PlaceDetail> {
        return placesLocalDataSource.getAllPlaces()
    }

    override suspend fun saveFavorite(place: PlaceDetail) {
        placesLocalDataSource.savePlace(place)
    }

    override suspend fun removeFavorite(id: String) {
        placesLocalDataSource.removePlace(id)
    }
}