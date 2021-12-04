package com.demo.places.di

import com.demo.places.data.repository.PlacesRepository
import com.demo.places.domain.repository.IPlacesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IPlacesRepository> { PlacesRepository(get(),get()) }
}