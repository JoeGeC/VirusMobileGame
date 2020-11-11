package com.example.virusgame.game.abilities

import com.example.virusgame.game.zombie.ZombieDamageHandler

abstract class AttackAbility(private var zombieDamageHandler: ZombieDamageHandler) : Ability(zombieDamageHandler.context){
    abstract val attackModifier: Float

    override fun effect() {
        zombieDamageHandler.inflictZombieDamage((zombieDamageHandler.getPlayerAttack() * attackModifier).toInt())
    }
}