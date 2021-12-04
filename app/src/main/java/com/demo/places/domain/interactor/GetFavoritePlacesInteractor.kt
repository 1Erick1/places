package com.demo.places.domain.interactor

import com.demo.places.domain.entity.PlaceDetail
import com.demo.places.domain.repository.IPlacesRepository

class GetFavoritePlacesInteractor(
    private val placesRepository: IPlacesRepository
) {
    suspend fun execute(): List<PlaceDetail>{
        return placesRepository.getFavoritePlaces()
    }
}