package com.demo.places.di

import com.demo.places.domain.interactor.*
import org.koin.dsl.module

val interactorModule = module {
    single { GetFavoritePlacesInteractor(get()) }
    single { GetPlaceDetailInteractor(get()) }
    single { RemoveFavoritePlaceInteractor(get()) }
    single { SaveFavoritePlaceInteractor(get()) }
    single { SearchPlacesInteractor(get()) }
}