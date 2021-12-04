package com.demo.places.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.demo.places.domain.interactor.SearchPlacesInteractor
import com.demo.places.presentation.base.BaseViewModel
import com.demo.places.presentation.model.PlaceResultModel
import com.google.android.gms.maps.model.LatLng

class SearchPlacesViewModel(
    private val searchPlacesInteractor: SearchPlacesInteractor
): BaseViewModel() {
    val places = MutableLiveData<List<PlaceResultModel>>()

    fun search(keyword: String, location: LatLng, radius: Int){
        execute {
            places.postValue(searchPlacesInteractor.execute(keyword,location.latitude,location.longitude,radius)
                .map { PlaceResultModel.fromDomain(it) })
        }
    }
}