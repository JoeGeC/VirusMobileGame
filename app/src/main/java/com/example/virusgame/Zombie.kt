package com.example.virusgame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

class Zombie(var image: Bitmap) {
    var x: Int = Resources.getSystem().displayMetrics.widthPixels / 2 - image.width / 2
    var y: Int = Resources.getSystem().displayMetrics.heightPixels / 2 - image.height / 2
    var health = 3

    fun draw(canvas: Canvas){
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

    fun update(){

    }
}
