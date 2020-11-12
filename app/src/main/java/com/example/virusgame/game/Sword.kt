package com.example.virusgame.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.virusgame.R
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2
import java.util.*

class Sword(context: Context) {
    private var position: Queue<IntVector2> = LinkedList<IntVector2>()
    private var image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.short_sword)
    var show: Boolean = false
    var active: Boolean = true
    val offset: FloatVector2 = FloatVector2(0.0f, image.width.toFloat())

    fun draw(canvas: Canvas) {
        if(show && active) {
            for(pos in position)
                canvas.drawBitmap(image, pos.x - offset.x, pos.y - offset.y, null)
        }
    }

    fun update(touchPos: IntVector2){
        position.add(touchPos)
        if(position.size > 3) position.remove()
        show = true
    }

    fun deactivate() {
        show = false
        position.clear()
    }
}