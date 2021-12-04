package com.demo.places.domain.interactor

import com.demo.places.domain.repository.IPlacesRepository

class RemoveFavoritePlaceInteractor(
    private val placesRepository: IPlacesRepository
) {
    suspend fun execute(id: String){
        placesRepository.removeFavorite(id)
    }
}