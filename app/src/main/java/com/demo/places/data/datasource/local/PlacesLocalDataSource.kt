package com.demo.places.data.datasource.local

import com.demo.places.data.datasource.local.dao.PlaceDao
import com.demo.places.data.datasource.local.dao.ReviewDao
import com.demo.places.data.datasource.local.db.PlacesDataBase
import com.demo.places.data.datasource.local.dto.PlaceDto
import com.demo.places.data.datasource.local.dto.ReviewDto
import com.demo.places.domain.entity.PlaceDetail

class PlacesLocalDataSource(
    private val placeDao: PlaceDao,
    private val reviewDao: ReviewDao
): IPlacesLocalDataSource {

    override suspend fun getPlaceDetail(id: String): PlaceDetail? {
        return placeDao.getPlaceById(id)?.toDomain()?.apply {
            reviews = reviewDao.getReviewsByPlace(id)?.map { it.toDomain() }
        }
    }

    override suspend fun getAllPlaces(): List<PlaceDetail> {
        return placeDao.getAll()
            .map { it.toDomain() }
    }

    override suspend fun savePlace(placeDetail: PlaceDetail) {
        placeDao.insertPlace(PlaceDto.fromDomain(placeDetail.apply { isFavorite = true }))
        placeDetail.reviews?.let {
            reviewDao.insertReviews(it.map { ReviewDto.fromDomain(it) })
        }
    }

    override suspend fun removePlace(placeId: String) {
        placeDao.deletePlace(placeId)
        reviewDao.deleteReviewsByPlace(placeId)
    }
}