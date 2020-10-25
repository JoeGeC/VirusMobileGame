package com.example.virusgame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

class Zombie(var image: Bitmap) {
    var x: Int = Resources.getSystem().displayMetrics.widthPixels / 2 - image.width / 2
    var y: Int = Resources.getSystem().displayMetrics.heightPixels / 2 - image.height / 2
    var rect: Rect get() { return Rect(x, y, x + image.width, y + image.height) } set(value) {}
    var health: Int = 3

    fun draw(canvas: Canvas){
        canvas.drawBitmap(image, x.toFloat(), y.toFloat(), null)
    }

    fun takeDamage(damage: Int) {
        health -= damage
        if(health <= 0){
            health = 0
            die()
        }
    }

    private fun die() {
    }
}
