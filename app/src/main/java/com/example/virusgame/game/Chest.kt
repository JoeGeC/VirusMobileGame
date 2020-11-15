package com.example.virusgame.game

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import com.example.virusgame.R
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.game.collector.CollectorManager
import com.example.virusgame.game.collector.GoldCollector
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2
import kotlin.random.Random

class Chest(private val collectorManager: CollectorManager) : CollectorDoneListener {
    private val closedImage = BitmapFactory.decodeResource(collectorManager.context.resources, R.drawable.chest_closed)
    private val openImage = BitmapFactory.decodeResource(collectorManager.context.resources, R.drawable.chest_open)
    private var activeImage = closedImage
    private var location = 0
    var position = FloatVector2(0f, ScreenDimensions.height / 1.4f - activeImage.height)
    private val midTopPosition get() = FloatVector2(position.x + activeImage.width / 2, position.y)
    var open = false
    var active = false

    private var fullRect: Rect get(){
        return Rect(position.x.toInt(), position.y.toInt(), position.x.toInt() + activeImage.width, position.y.toInt() + activeImage.height)
    } set(value) {}

    fun spawn(){
        location = Random.nextInt(-180, 180)
        active = true
    }

    fun update(azimuth: Double){
        setPositionOnScreen(azimuth)
    }

    fun onTouch(startTouchPos: IntVector2, endTouchPos: IntVector2){
        if(startTouchPos.isInside(fullRect) && endTouchPos.isInside(fullRect)) openChest()
    }

    private fun setPositionOnScreen(azimuth: Double) {
        var distanceToChest = azimuth - location
        if(distanceToChest < -180) distanceToChest += 360
        else if(distanceToChest > 180) distanceToChest -= 360
        position.x = (distanceToChest * (ScreenDimensions.width / 180)).toFloat()
    }

    fun draw(canvas: Canvas){
        if(active) canvas.drawBitmap(activeImage, position.x, position.y, null)
    }

    private fun openChest() {
        if(!active || open) return
        val random = Random.nextInt(100)
        if(random < 9) collectorManager.addCollector(GoldCollector(midTopPosition, 10, collectorManager, this))
        else collectorManager.addCollector(ZombieHeartCollector(midTopPosition, 10, collectorManager, this))
        open = true
        activeImage = openImage
    }

    override fun onCollectorDone() {
        active = false
        open = false
        activeImage = closedImage
    }
}