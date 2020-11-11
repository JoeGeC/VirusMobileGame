package com.example.virusgame.game.abilities

import android.content.Context
import android.graphics.Bitmap
import com.example.virusgame.clock.Clock
import com.example.virusgame.game.zombie.ZombieDamageHandler

abstract class Ability(@Transient val context: Context) {
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
