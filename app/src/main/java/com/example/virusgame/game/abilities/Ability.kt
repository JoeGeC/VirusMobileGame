package com.example.virusgame.game.abilities

import android.graphics.Bitmap
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.ZombieDamageHandler

abstract class Ability(@Transient var zombieDamageHandler: ZombieDamageHandler) {
    val context = zombieDamageHandler.context
    abstract val name: String
    abstract val iconBitmap: Bitmap
    abstract val coolDownTime: Int
    var lastAbilityUseTime: Long = 0
    abstract fun effect()

    fun use(): Boolean {
        if(Clock.haveMillisecondsPassedSince(lastAbilityUseTime, coolDownTime)){
            lastAbilityUseTime = System.nanoTime()
            effect()
            return true
        }
        return false
    }
}
