package viruswiper.game

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import viruswiper.R
import viruswiper.ScreenDimensions
import viruswiper.game.collector.CollectorManager
import viruswiper.game.collector.GoldCollector
import viruswiper.game.collector.PotionCollector
import viruswiper.game.collector.ZombieHeartCollector
import viruswiper.game.rotation.AzimuthEntity
import viruswiper.game.vector2.FloatVector2
import viruswiper.game.vector2.IntVector2
import kotlin.math.pow
import kotlin.random.Random

class Chest(private val collectorManager: CollectorManager) : AzimuthEntity(),
    CollectorDoneListener {
    private val closedImage = BitmapFactory.decodeResource(collectorManager.context.resources, R.drawable.chest_closed)
    private val openImage = BitmapFactory.decodeResource(collectorManager.context.resources, R.drawable.chest_open)
    private var activeImage = closedImage
    override var position = FloatVector2(0f, ScreenDimensions.height / 1.4f - activeImage.height)
    private val midTopPosition get() = FloatVector2(position.x + activeImage.width / 2, position.y)
    private var open = false
    private var health = 1
    var gold = 10
    var active = false

    private var fullRect: Rect get(){
        return Rect(position.x.toInt(), position.y.toInt(), position.x.toInt() + activeImage.width, position.y.toInt() + activeImage.height)
    } set(value) {}

    fun spawn(wave: Int){
        gold = (wave.toFloat().pow(1.5f) * 7).toInt()
        health = wave * 2
        setLocation()
        active = true
    }

    fun update(azimuth: Double){
        setPositionOnScreen(azimuth)
    }

    fun onTouch(startTouchPos: IntVector2, endTouchPos: IntVector2){
        if(startTouchPos.isInside(fullRect) && endTouchPos.isInside(fullRect)) openChest()
    }

    fun draw(canvas: Canvas){
        if(active) canvas.drawBitmap(activeImage, position.x, position.y, null)
    }

    private fun openChest() {
        if(!active || open) return
        val random = Random.nextInt(100)
        when {
            random < 90 -> collectorManager.addCollector(GoldCollector(midTopPosition, gold, collectorManager, this))
            random < 99 -> collectorManager.addCollector(PotionCollector(midTopPosition, health, collectorManager, this))
            else -> collectorManager.addCollector(ZombieHeartCollector(midTopPosition, 1, collectorManager, this))
        }
        open = true
        activeImage = openImage
    }

    override fun onCollectorDone() {
        reset()
    }

    fun reset(){
        active = false
        open = false
        activeImage = closedImage
    }
}