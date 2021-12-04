package com.demo.places.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.places.data.datasource.local.dto.PlaceDto

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place WHERE id=:id")
    suspend fun getPlaceById(id: String): PlaceDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: PlaceDto)

    @Query("DELETE from place where id =:id")
    suspend fun deletePlace(id: String)
}