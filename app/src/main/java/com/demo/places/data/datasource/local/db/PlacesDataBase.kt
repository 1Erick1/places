package com.demo.places.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.places.data.datasource.local.dao.PlaceDao
import com.demo.places.data.datasource.local.dao.ReviewDao
import com.demo.places.data.datasource.local.dto.PlaceDto
import com.demo.places.data.datasource.local.dto.ReviewDto

@Database(entities = [ReviewDto::class,PlaceDto::class], version = 1)
abstract class PlacesDataBase: RoomDatabase() {
    abstract fun reviewDao(): ReviewDao
    abstract fun placeDao(): PlaceDao
}