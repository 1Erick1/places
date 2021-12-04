package com.demo.places.di

import android.content.Context
import androidx.room.Room
import com.demo.places.data.datasource.local.IPlacesLocalDataSource
import com.demo.places.data.datasource.local.PlacesLocalDataSource
import com.demo.places.data.datasource.local.db.PlacesDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { providePlacesDataBase(androidContext()) }
    single<IPlacesLocalDataSource> { PlacesLocalDataSource(get()) }
}

fun providePlacesDataBase(context: Context): PlacesDataBase{
    return Room.databaseBuilder(context,PlacesDataBase::class.java,"Places.db")
        .build()
}