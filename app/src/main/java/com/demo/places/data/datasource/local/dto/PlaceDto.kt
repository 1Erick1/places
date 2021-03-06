package com.demo.places.data.datasource.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.places.domain.entity.PlaceDetail

@Entity(tableName = "place")
data class PlaceDto(
    @PrimaryKey val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val rating: Double?,
    val picture: String?,
    val iconUrl: String?,
    val iconBgColor: String?
){
    fun toDomain(): PlaceDetail{
        return PlaceDetail(
            id = id,
            name = name,
            latitude = latitude,
            longitude = longitude,
            address = address,
            rating = rating,
            picture = picture,
            iconUrl = iconUrl,
            iconBgColor = iconBgColor,
            isFavorite = true
        )
    }

    companion object{
        fun fromDomain(place: PlaceDetail): PlaceDto{
            return PlaceDto(
                id = place.id,
                name = place.name,
                latitude = place.latitude,
                longitude = place.longitude,
                address = place.address,
                rating = place.rating,
                picture = place.picture,
                iconUrl = place.iconUrl,
                iconBgColor = place.iconBgColor
            )
        }
    }
}