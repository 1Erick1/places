package com.demo.places.presentation.util

import android.graphics.*

fun Bitmap.tint(color: String): Bitmap{
    val paint = Paint()
    paint.colorFilter = PorterDuffColorFilter(Color.parseColor(color),PorterDuff.Mode.SRC_IN)
    val result = Bitmap.createBitmap(this.width,this.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(result)
    canvas.drawBitmap(this,0f,0f,paint)
    return result
}