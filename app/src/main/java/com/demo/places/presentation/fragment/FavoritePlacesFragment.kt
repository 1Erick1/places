package com.demo.places.presentation.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.MutableLiveData
import com.demo.places.R
import com.demo.places.databinding.FragmentFavoritePlacesBinding
import com.demo.places.presentation.activity.PlaceDetailActivity
import com.demo.places.presentation.adapter.FavoriteAdapter
import com.demo.places.presentation.base.BaseFragment
import com.demo.places.presentation.model.PlaceDetailModel
import com.demo.places.presentation.util.showToast
import com.demo.places.presentation.viewModel.FavoritePlacesViewModel
import org.koin.android.ext.android.inject

class FavoritePlacesFragment: BaseFragment<FragmentFavoritePlacesBinding>() {
    private val viewModel: FavoritePlacesViewModel by inject()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritePlacesBinding {
        return FragmentFavoritePlacesBinding.inflate(layoutInflater,container,false)
    }

    override fun setupView() {
        adapter = FavoriteAdapter { goToDetail(it) }
        binding.rvPlaces.adapter = adapter
        viewModel.getFavoritePlaces()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                viewModel.getFavoritePlaces()
            }
        }
    }

    private fun goToDetail(place: PlaceDetailModel) {
        resultLauncher.launch(PlaceDetailActivity.newInstance(requireActivity(),place.id))
    }

    override fun setupEvents() {
    }

    override fun setupObservers() {
        viewModel.places.observe(viewLifecycleOwner,{
            adapter.setItems(it)
        })

        viewModel.empty.observe(viewLifecycleOwner,{
            binding.tvEmpty.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden.not()){
            viewModel.getFavoritePlaces()
        }
    }

    override fun getErrorObservers(): List<MutableLiveData<Exception>>? {
        return listOf(viewModel.error)
    }
}