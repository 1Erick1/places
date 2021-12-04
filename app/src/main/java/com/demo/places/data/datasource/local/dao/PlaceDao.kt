package com.demo.places.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.places.data.datasource.local.dto.PlaceDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place WHERE id=:id")
    suspend fun getPlaceById(id: String): PlaceDto?

    @Query("SELECT * FROM place")
    suspend fun getAll(): List<PlaceDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: PlaceDto)

    @Query("DELETE from place where id =:id")
    suspend fun deletePlace(id: String)
}