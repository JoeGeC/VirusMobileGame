package com.example.virusgame.game.abilities

import com.example.virusgame.R
import com.example.virusgame.game.ZombieDamageHandler

class DoubleDamageAbility(zombieDamageHandler: ZombieDamageHandler) : Ability(zombieDamageHandler) {
    override var name: String = context.getString(R.string.doubleDamage)

    override fun use() {
        zombieDamageHandler.inflictZombieDamage(zombieDamageHandler.getPlayerAttack() * 2)
    }
}