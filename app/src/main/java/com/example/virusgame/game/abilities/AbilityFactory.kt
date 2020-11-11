package com.example.virusgame.game.abilities

import com.example.virusgame.R
import com.example.virusgame.game.zombie.ZombieDamageHandler

class AbilityFactory {
    fun createAbility(abilityName: String, zombieDamageHandler: ZombieDamageHandler): Ability? {
        if(abilityName == zombieDamageHandler.context.getString(R.string.fireAbility))
            return FireAbility(zombieDamageHandler)
        if(abilityName == zombieDamageHandler.context.getString(R.string.lightningAbility))
            return LightningAbility(zombieDamageHandler)
        if(abilityName == zombieDamageHandler.context.getString(R.string.iceAbility))
            return IceAbility(zombieDamageHandler)
        return null
    }
}