package com.demo.places.presentation.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.demo.places.R
import com.demo.places.databinding.ActivityPlaceDetailBinding
import com.demo.places.presentation.adapter.ReviewAdapter
import com.demo.places.presentation.base.BaseActivity
import com.demo.places.presentation.model.PlaceDetailModel
import com.demo.places.presentation.util.load
import com.demo.places.presentation.util.tint
import com.demo.places.presentation.viewModel.PlaceDetailViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject

class PlaceDetailActivity: BaseActivity<ActivityPlaceDetailBinding>(), OnMapReadyCallback {
    private val viewModel: PlaceDetailViewModel by inject()
    private val reviewAdapter = ReviewAdapter()
    private var place: PlaceDetailModel? = null
    private var googleMap: GoogleMap? = null

    companion object{
        const val ARG_PLACE_ID = "arg_place_id"
        fun newInstance(context: Context, placeId: String) = Intent(
            context,
            PlaceDetailActivity::class.java
        ).apply { putExtra(ARG_PLACE_ID,placeId) }
    }

    override fun getViewBinding(): ActivityPlaceDetailBinding {
        return ActivityPlaceDetailBinding.inflate(layoutInflater)
    }

    override fun setupView() {
        supportActionBar?.setTitle(R.string.title_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.rvReviews.adapter = reviewAdapter
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun setupEvents() {

    }

    override fun setupObservers() {
        viewModel.place.observe(this,{
            place = it
            showDetail(it)
        })

        viewModel.progress.observe(this,{
            binding.vProgress.visibility = if (it) View.VISIBLE else View.GONE
            binding.viewContent.visibility = if (it) View.GONE else View.VISIBLE
        })

        intent.getStringExtra(ARG_PLACE_ID)?.let {
            viewModel.getPlaceDetail(it)
        }
    }

    private fun showDetail(place: PlaceDetailModel) {
        with(binding){
            place.picture?.let { ivPlace.load(it) }
            tvName.text = place.name
            tvAddress.text = place.address
            tvRating.text = place.rating.toString()
            place.rating?.let { ratingBar.rating = it.toFloat() }
            cbFavorite.isChecked = place.isFavorite
        }
        place.reviews?.let { reviewAdapter.setItems(it) }
        showPlaceLocation(place)
        setupFavorite()
    }

    private fun setupFavorite(){
        binding.cbFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            place?.let {
                setResult(Activity.RESULT_OK)
                if (isChecked){
                    viewModel.saveFavorite(it)
                }else{
                    viewModel.removeFavorite(it.id)
                }
            }
        }
    }

    override fun getErrorObservers(): List<MutableLiveData<Exception>>? {
        return listOf(viewModel.error)
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap
    }

    private fun showPlaceLocation(place: PlaceDetailModel){
        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_marker_large)
        googleMap?.addMarker(
            MarkerOptions()
                .position(place.location)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap.tint(place.markerColor?:"#FF03DAC5")))
        )?.tag = place.id

        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(place.location,15f))
    }
}