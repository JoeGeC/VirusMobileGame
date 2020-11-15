package com.example.virusgame.game

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.virusgame.MainActivity
import com.example.virusgame.R
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.game.collector.Collector
import com.example.virusgame.game.collector.CollectorManager
import com.example.virusgame.game.collector.GoldCollector
import com.example.virusgame.game.vector2.FloatVector2
import kotlin.random.Random

class Chest(private val collectorManager: CollectorManager, context: Context) {
    private var closedImage = BitmapFactory.decodeResource(context.resources, R.drawable.chest_closed)
    private var openImage = BitmapFactory.decodeResource(context.resources, R.drawable.chest_open)
    private var location = 0
    var position = FloatVector2(0f, ScreenDimensions.height / 1.8f)
    var open = false
    var active = false

    fun spawn(){
        location = Random.nextInt(-180, 180)
        active = true
    }

    fun update(azimuth: Double){
        setPositionOnScreen(azimuth)
    }

    fun draw(canvas: Canvas){
        if(active) canvas.drawBitmap(openImage, position.x, position.y, null)
    }

    private fun setPositionOnScreen(azimuth: Double) {
        var distanceToChest = azimuth - location
        if(distanceToChest < -180) distanceToChest += 360
        else if(distanceToChest > 180) distanceToChest -= 360
        position.x = (distanceToChest * (ScreenDimensions.width / 180)).toFloat()
    }

    fun onTouch(){
        if(open) return
        openChest()
    }

    private fun openChest() {
//        val random = Random.nextInt(10)
//        if(random < 9)
            collectorManager.addCollector(GoldCollector(position, 10, collectorManager))
        open = true
    }
}