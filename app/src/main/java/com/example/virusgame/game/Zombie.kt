package com.example.virusgame.game

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

class Zombie(var image: Bitmap) : SwipeTaker {
    var x: Int = Resources.getSystem().displayMetrics.widthPixels / 2 - image.width / 2
    var y: Int = Resources.getSystem().displayMetrics.heightPixels / 2 - image.height / 2
    override var rect: Rect get() { return Rect(x, y, x + image.width, y + image.height) } set(value) {}
    var health: Int = 100

    fun draw(canvas: Canvas){
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
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
