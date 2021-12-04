package com.demo.places.presentation.util

import android.location.Location
import com.google.android.gms.maps.model.LatLng

fun LatLng.distanceTo(end: LatLng): Float{
    val results = FloatArray(2)
    Location.distanceBetween(latitude,longitude,end.latitude,end.longitude,results)
    return results[0]
}