package com.demo.places.presentation.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(url: String){
    if (url.isNotEmpty()){
        Picasso.get().load(url).into(this)
    }
}

fun ImageView.loadCircle(url: String){
    Picasso.get()
        .load(url)
        .transform(CircleTransform())
        .into(this)
}