package com.example.virusgame.game.abilities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.virusgame.R
import com.example.virusgame.game.zombie.ZombieDamageHandler

class FireAbility(private val zombieDamageHandler: ZombieDamageHandler) : Ability(zombieDamageHandler.context) {
    override val name: String = context.getString(R.string.fireAbility)
    override val iconBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.fireball_ability_icon)
    override val coolDownTime: Int = 10000

    override fun effect() {
        zombieDamageHandler.inflictZombieDamage(zombieDamageHandler.getPlayerAttack() * 2)
    }
}