package com.demo.places.data.datasource.local

import com.demo.places.data.datasource.local.db.PlacesDataBase
import com.demo.places.data.datasource.local.dto.PlaceDto
import com.demo.places.data.datasource.local.dto.ReviewDto
import com.demo.places.domain.entity.PlaceDetail

class PlacesLocalDataSource(
    private val placesDataBase: PlacesDataBase
): IPlacesLocalDataSource {

    override suspend fun getPlaceDetail(id: String): PlaceDetail? {
        return placesDataBase.placeDao().getPlaceById(id)?.toDomain()?.apply {
            reviews = placesDataBase.reviewDao().getReviewsByPlace(id)?.map { it.toDomain() }
        }
    }

    override suspend fun getAllPlaces(): List<PlaceDetail> {
        return placesDataBase.placeDao().getAll()
            .map { it.toDomain() }
    }

    override suspend fun savePlace(placeDetail: PlaceDetail) {
        placesDataBase.placeDao().insertPlace(PlaceDto.fromDomain(placeDetail.apply { isFavorite = true }))
        placeDetail.reviews?.let {
            placesDataBase.reviewDao().insertReviews(it.map { ReviewDto.fromDomain(it) })
        }
    }

    override suspend fun removePlace(placeId: String) {
        placesDataBase.placeDao().deletePlace(placeId)
        placesDataBase.reviewDao().deleteReviewsByPlace(placeId)
    }
}