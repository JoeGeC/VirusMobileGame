package com.example.virusgame.game.abilities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.virusgame.R
import com.example.virusgame.game.zombie.ZombieDamageHandler

class IceAbility(zombieDamageHandler: ZombieDamageHandler) : AttackAbility(zombieDamageHandler) {
    override val name: String = context.getString(R.string.iceAbility)
    override val iconBitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ice_ability_icon)
    override val coolDownTime: Int = 15000
    override val attackModifier: Float = 7f
    override val animationFrames = arrayOf<Bitmap>()
}
