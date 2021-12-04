package com.demo.places.di

import com.demo.places.presentation.viewModel.FavoritePlacesViewModel
import com.demo.places.presentation.viewModel.PlaceDetailViewModel
import com.demo.places.presentation.viewModel.SearchPlacesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FavoritePlacesViewModel(get()) }
    viewModel { SearchPlacesViewModel(get()) }
    viewModel { PlaceDetailViewModel(get(),get(),get()) }
}