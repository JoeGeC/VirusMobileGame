package com.example.virusgame.game

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.MainActivity
import com.example.virusgame.R
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.game.vector2.FloatVector2

class Collector(private var position: FloatVector2, imageResource: Int, private val amount: Int, private val collectorManager: CollectorManager) {
    private val context = MainActivity.applicationContext()
    private val image = BitmapFactory.decodeResource(context.resources, imageResource)
    private var count = 0
    private var paint: Paint = Paint()

    init {
        paint.color = ContextCompat.getColor(context, R.color.white)
        paint.textSize = ScreenDimensions.height / 40.0f
        paint.typeface = ResourcesCompat.getFont(context, R.font.unispace)
        paint.textAlign = Paint.Align.CENTER
    }

    fun draw(canvas: Canvas){
        canvas.drawBitmap(image, position.x, position.y, null)
        canvas.drawText("+$amount", position.x + 20, position.y, paint)
        position.y--
        count++
        if(count >= 30) collectorManager.destroyCollector(this)
    }
}