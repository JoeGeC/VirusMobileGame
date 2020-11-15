package com.example.virusgame.game.collector

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.MainActivity
import com.example.virusgame.R
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.game.vector2.FloatVector2

abstract class Collector(private var position: FloatVector2, protected val amount: Int, protected val collectorManager: CollectorManager) {
    protected val context = MainActivity.applicationContext()
    abstract val image: Bitmap
    private var count = 0
    private var paint: Paint = Paint()

    init {
        paint.color = ContextCompat.getColor(context, R.color.white)
        paint.textSize = ScreenDimensions.height / 40.0f
        paint.typeface = ResourcesCompat.getFont(context, R.font.unispace)
        paint.textAlign = Paint.Align.CENTER
        this.collect()
    }

    abstract fun collect()

    fun draw(canvas: Canvas){
        canvas.drawBitmap(image, position.x, position.y, null)
        canvas.drawText("+$amount", position.x + 20, position.y, paint)
        position.y--
        count++
        if(count >= 30) collectorManager.destroyCollector(this)
    }
}