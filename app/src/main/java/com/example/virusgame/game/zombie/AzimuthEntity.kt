package com.example.virusgame.game.zombie

import com.example.virusgame.ScreenDimensions
import com.example.virusgame.game.vector2.FloatVector2
import kotlin.random.Random

abstract class AzimuthEntity() {
    private var location: Int = 0
    open lateinit var position: FloatVector2

    protected fun setPositionOnScreen(azimuth: Double) {
        var distanceToZombie = azimuth - location
        if(distanceToZombie < -180) distanceToZombie += 360
        else if(distanceToZombie > 180) distanceToZombie -= 360
        position.x = (distanceToZombie * (ScreenDimensions.width / 180)).toFloat()
    }

    protected fun setLocation(){
        location = Random.nextInt(-180, 180)
    }
}
