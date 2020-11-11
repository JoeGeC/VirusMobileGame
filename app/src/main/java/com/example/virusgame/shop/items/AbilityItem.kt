package com.example.virusgame.shop.items

import com.example.virusgame.game.abilities.AbilityFactory
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.zombie.ZombieDamageHandler

abstract class AbilityItem: ShopItem() {
    override fun use(player: Player, zombieDamageHandler: ZombieDamageHandler) {
        player.ability = AbilityFactory().createAbility(itemName, zombieDamageHandler)
    }
}