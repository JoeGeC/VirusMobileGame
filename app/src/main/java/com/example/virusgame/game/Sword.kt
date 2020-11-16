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
    private var position: Queue<FloatVector2> = LinkedList<FloatVector2>()
    private var image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.short_sword)
    private var show: Boolean = false
    var active: Boolean = true
    var offset = IntVector2(image.width, image.height)

    fun draw(canvas: Canvas) {
        if(show && active) {
            for(pos in position)
                canvas.drawBitmap(image, pos.x, pos.y - image.width, null)
        }
    }

    fun update(touchPos: IntVector2){
        position.add(touchPos.toFloat())
        if(position.size > 3) position.remove()
        show = true
    }

    fun deactivate() {
        show = false
        position.clear()
    }
}