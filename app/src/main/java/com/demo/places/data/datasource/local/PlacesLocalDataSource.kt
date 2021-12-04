package com.demo.places.data.datasource.local

import com.demo.places.data.datasource.local.db.PlacesDataBase
import com.demo.places.data.datasource.local.dto.PlaceDto
import com.demo.places.domain.entity.PlaceDetail

class PlacesLocalDataSource(
    private val placesDataBase: PlacesDataBase
): IPlacesLocalDataSource {

    override suspend fun getPlaceDetail(id: String): PlaceDetail? {
        return placesDataBase.placeDao().getPlaceById(id)?.toDomain()
    }

    override suspend fun savePlace(placeDetail: PlaceDetail) {
        placesDataBase.placeDao().insertPlace(PlaceDto.fromDomain(placeDetail))
    }

    override suspend fun removePlace(placeId: String) {
        placesDataBase.placeDao().deletePlace(placeId)
    }
}