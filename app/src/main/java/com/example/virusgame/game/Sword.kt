package com.example.virusgame.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.virusgame.R
import java.util.*

class Sword(context: Context) {
    private var position: Queue<Vector2> = LinkedList<Vector2>()
    private var image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.short_sword)
    var active: Boolean = false

    fun draw(canvas: Canvas) {
        if(active) {
            for(pos in position)
                canvas.drawBitmap(image, pos.x, pos.y - image.width, null)
        }
    }

    fun update(xTouch: Float, yTouch: Float){
        position.add(Vector2(xTouch, yTouch))
        if(position.size > 3) position.remove()
        active = true
    }
}