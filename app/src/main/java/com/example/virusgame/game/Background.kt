package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.virusgame.R

class Background (context: Context){
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private val background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.city_background), screenWidth, screenHeight, false)

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(background, 0f, 0f, null)
    }
}
