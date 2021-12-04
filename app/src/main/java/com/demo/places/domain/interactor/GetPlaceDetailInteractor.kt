package com.demo.places.domain.interactor

import com.demo.places.domain.entity.PlaceDetail
import com.demo.places.domain.repository.IPlacesRepository

class GetPlaceDetailInteractor(
    private val placesRepository: IPlacesRepository
) {
    suspend fun execute(id: String): PlaceDetail{
        return placesRepository.getPlaceDetail(id)
    }
}