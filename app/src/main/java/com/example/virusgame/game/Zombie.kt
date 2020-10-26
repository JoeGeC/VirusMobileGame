package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.example.virusgame.R

class Zombie(var image: Bitmap, context: Context) : SwipeTaker {
    private var screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private var screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private var x: Int = screenWidth / 2 - image.width / 2
    private var y: Int = screenHeight / 2 - image.height / 2
    override var rect: Rect get() { return Rect(x, y, x + image.width, y + image.height) } set(value) {}
    var health: Int = 3
    private val zombieHealthPaint: Paint = Paint()

    init {
        zombieHealthPaint.textSize = 40.0f
        zombieHealthPaint.color = ContextCompat.getColor(context, R.color.white)
    }

    fun draw(canvas: Canvas){
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
        canvas.drawText("Health: $health", screenWidth - 250.0f, 100.0f, zombieHealthPaint)
    }

    private fun takeDamage(damage: Int) {
        health -= damage
        if(health <= 0){
            health = 0
            die()
        }
    }

    private fun die() {
    }

    override fun onSuccessfulSwipe() {
        takeDamage(1)
    }
}
