package com.demo.places.domain.interactor

import com.demo.places.domain.entity.PlaceDetail
import com.demo.places.domain.repository.IPlacesRepository

class SaveFavoritePlaceInteractor(
    private val placesRepository: IPlacesRepository
) {
    suspend fun execute(place: PlaceDetail){
        placesRepository.saveFavorite(place)
    }
}