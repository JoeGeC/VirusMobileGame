package com.example.virusgame.game.abilities

import com.example.virusgame.game.ZombieDamageHandler

class AbilityFactory {
    fun createAbility(abilityName: String, zombieDamageHandler: ZombieDamageHandler): Ability? {
        if(abilityName == "DoubleDamage")
            return DoubleDamageAbility(zombieDamageHandler)
        return null
    }
}