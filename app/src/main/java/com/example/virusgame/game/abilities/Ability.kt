package com.example.virusgame.game.abilities

import com.example.virusgame.game.ZombieDamageHandler

abstract class Ability(@Transient var zombieDamageHandler: ZombieDamageHandler) {
    val context = zombieDamageHandler.context
    abstract var name: String
    abstract fun use()
}
