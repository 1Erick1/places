package com.demo.places.presentation.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.demo.places.R
import com.demo.places.databinding.FragmentNearbyPlacesBinding
import com.demo.places.presentation.activity.PlaceDetailActivity
import com.demo.places.presentation.base.BaseFragment
import com.demo.places.presentation.model.PlaceResultModel
import com.demo.places.presentation.util.distanceTo
import com.demo.places.presentation.util.showToast
import com.demo.places.presentation.util.tint
import com.demo.places.presentation.viewModel.SearchPlacesViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.ext.android.inject

class SearchPlaceFragment: BaseFragment<FragmentNearbyPlacesBinding>(), OnMapReadyCallback {
    private val viewModel: SearchPlacesViewModel by inject()
    private var location: LatLng? = null
    private var googleMap: GoogleMap? = null
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNearbyPlacesBinding {
        return FragmentNearbyPlacesBinding.inflate(layoutInflater,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPermissionCallback()
    }

    override fun setupView() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun setupEvents() {
        with(binding){
            etSearch.setOnEditorActionListener { v, actionId, event ->
                if (actionId==EditorInfo.IME_ACTION_SEARCH){
                    searchKeyword()
                }
                false
            }

            ivSearch.setOnClickListener {
                searchKeyword()
            }
        }
    }

    private fun searchKeyword(){
        location = googleMap?.cameraPosition?.target
        if (binding.etSearch.text.toString().length>1){
            location?.let { viewModel.search(binding.etSearch.text.toString(),it, SEARCH_RADIUS,) }
        }
    }

    override fun setupObservers() {
        viewModel.places.observe(viewLifecycleOwner, {places->
            googleMap?.let {
                googleMap?.clear()
                for (place in places){
                    addMarker(place)
                }
            }
        })

        viewModel.progress.observe(viewLifecycleOwner,{
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.empty.observe(viewLifecycleOwner,{
            requireActivity().showToast(getString(R.string.no_results_nearby),Toast.LENGTH_SHORT)
        })
    }

    @SuppressLint("MissingPermission")
    private fun registerPermissionCallback() {
        requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    googleMap?.isMyLocationEnabled=true
                    getCurrentLocation()
                } else {
                    requireActivity().showToast(getString(R.string.location_permission_not_granted))
                }
            }
    }

    override fun getErrorObservers(): List<MutableLiveData<Exception>>? {
        return listOf(viewModel.error)
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap
        googleMap?.uiSettings?.isMapToolbarEnabled = false
        googleMap?.setOnInfoWindowClickListener {
            goToDetail(it.tag as String)
        }

        googleMap?.setOnCameraIdleListener {
            location?.let {
                googleMap?.cameraPosition?.target?.let { newLoc->
                    if (it.distanceTo(newLoc)> SEARCH_RADIUS){
                        searchKeyword()
                    }
                }

            }
        }

        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }
        googleMap?.isMyLocationEnabled = true
        getCurrentLocation()
    }

    private fun goToDetail(id: String) {
        startActivity(PlaceDetailActivity.newInstance(requireActivity(),id))
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        LocationServices.getFusedLocationProviderClient(requireActivity()).lastLocation.addOnSuccessListener {
            location = LatLng(it.latitude,it.longitude)
            location?.let { googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(it,15f)) }
        }
    }

    private fun addMarker(place: PlaceResultModel){
        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_marker_large)
        googleMap?.addMarker(
            MarkerOptions()
                .position(place.location)
                .title(place.name)
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap.tint(place.markerColor?:"#FF03DAC5")))
        )?.tag = place.id
    }

    companion object{
        const val SEARCH_RADIUS = 1500
    }
}