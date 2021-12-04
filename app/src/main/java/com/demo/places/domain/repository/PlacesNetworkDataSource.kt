package com.demo.places.domain.repository

import com.demo.places.data.datasource.network.IPlacesNetworkDataSource
import com.demo.places.data.datasource.network.service.PlacesService
import com.demo.places.domain.entity.PlaceDetail
import com.demo.places.domain.entity.PlaceResult

class PlacesNetworkDataSource(
    private val placesService: PlacesService
): IPlacesNetworkDataSource {

    override suspend fun searchPlaces(
        keyword: String,
        latitude: Double,
        longitude: Double,
        radius: Int
    ): List<PlaceResult> {
        return placesService.searchNearby(keyword,"$latitude,$longitude",radius).results
            .map { it.toDomain() }
    }

    override suspend fun getPlaceDetail(id: String): PlaceDetail {
        return placesService.getPlaceDetail(id).detail.toDomain()
    }
}