package com.demo.places.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.demo.places.domain.interactor.GetPlaceDetailInteractor
import com.demo.places.domain.interactor.RemoveFavoritePlaceInteractor
import com.demo.places.domain.interactor.SaveFavoritePlaceInteractor
import com.demo.places.presentation.base.BaseViewModel
import com.demo.places.presentation.model.PlaceDetailModel

class PlaceDetailViewModel(
    private val getPlaceDetailInteractor: GetPlaceDetailInteractor,
    private val saveFavoritePlaceInteractor: SaveFavoritePlaceInteractor,
    private val removeFavoritePlaceInteractor: RemoveFavoritePlaceInteractor
): BaseViewModel() {
    val place = MutableLiveData<PlaceDetailModel>()

    fun getPlaceDetail(id: String){
        execute {
            place.postValue(PlaceDetailModel.fromDomain(getPlaceDetailInteractor.execute(id)))
        }
    }

    fun saveFavorite(place: PlaceDetailModel){
        execute {
            saveFavoritePlaceInteractor.execute(place.toDomain())
        }
    }

    fun removeFavorite(id: String){
        execute {
            removeFavoritePlaceInteractor.execute(id)
        }
    }
}