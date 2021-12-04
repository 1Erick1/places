package com.demo.places.domain.interactor

import com.demo.places.domain.entity.PlaceResult
import com.demo.places.domain.repository.IPlacesRepository

class SearchPlacesInteractor(
    private val placesRepository: IPlacesRepository
) {
    suspend fun execute(keyword: String, latitude: Double, longitude: Double, radius: Int): List<PlaceResult>{
        return placesRepository.searchPlaces(keyword, latitude, longitude, radius)
    }
}