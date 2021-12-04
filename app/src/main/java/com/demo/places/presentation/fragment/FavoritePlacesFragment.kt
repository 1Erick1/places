package com.demo.places.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.demo.places.databinding.FragmentFavoritePlacesBinding
import com.demo.places.presentation.adapter.FavoriteAdapter
import com.demo.places.presentation.base.BaseFragment
import com.demo.places.presentation.model.PlaceDetailModel
import com.demo.places.presentation.viewModel.FavoritePlacesViewModel
import org.koin.android.ext.android.inject

class FavoritePlacesFragment: BaseFragment<FragmentFavoritePlacesBinding>() {
    private val viewModel: FavoritePlacesViewModel by inject()
    private lateinit var adapter: FavoriteAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritePlacesBinding {
        return FragmentFavoritePlacesBinding.inflate(layoutInflater,container,false)
    }

    override fun setupView() {
        adapter = FavoriteAdapter { goToDetail(it) }
        binding.rvPlaces.adapter = adapter
    }

    private fun goToDetail(place: PlaceDetailModel) {

    }

    override fun setupEvents() {
    }

    override fun setupObservers() {
        viewModel.places.observe(viewLifecycleOwner,{
            adapter.setItems(it)
        })
        viewModel.getFavoritePlaces()
    }

    override fun getErrorObservers(): List<MutableLiveData<Exception>>? {
        return listOf(viewModel.error)
    }
}