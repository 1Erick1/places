package com.demo.places.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.demo.places.domain.interactor.GetFavoritePlacesInteractor
import com.demo.places.presentation.base.BaseViewModel
import com.demo.places.presentation.model.PlaceDetailModel

class FavoritePlacesViewModel(
    private val getFavoritePlacesInteractor: GetFavoritePlacesInteractor
): BaseViewModel() {
    val places= MutableLiveData<List<PlaceDetailModel>>()

    fun getFavoritePlaces(){
        execute {
            places.postValue(getFavoritePlacesInteractor.execute()
                .map { PlaceDetailModel.fromDomain(it) })
        }
    }
}