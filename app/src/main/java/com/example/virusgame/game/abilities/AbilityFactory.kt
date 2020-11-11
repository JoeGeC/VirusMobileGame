package com.example.virusgame.game.abilities

import com.example.virusgame.game.zombie.ZombieDamageHandler

class AbilityFactory {
    fun createAbility(abilityName: String, zombieDamageHandler: ZombieDamageHandler): Ability? {
        if(abilityName == "Fire Attack")
            return FireAbility(zombieDamageHandler)
        return null
    }
}