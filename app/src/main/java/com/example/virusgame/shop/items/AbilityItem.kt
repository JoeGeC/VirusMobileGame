package com.example.virusgame.shop.items

import com.example.virusgame.game.abilities.AbilityFactory
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.zombie.ZombieDamageHandler
import com.example.virusgame.shop.ShopListAdapter

abstract class AbilityItem: ShopItem() {
    private var bought: Boolean = false

    override fun use(player: Player, zombieDamageHandler: ZombieDamageHandler) {
        equipped = true
        player.ability = AbilityFactory().createAbility(itemName, zombieDamageHandler)
    }

    override fun isEquipped(): Boolean {
        return equipped
    }

    override fun getPriceAsString(): String {
        if(bought) return "0"
        return price.toString()
    }

    override fun onClick(adapter: ShopListAdapter) {
        if(bought) equipItem(adapter)
        else if(adapter.shopHandler.purchase(this)){
            bought = true
            equipItem(adapter)
        }
    }

    private fun equipItem(adapter: ShopListAdapter) {
        adapter.unequipAll()
        adapter.shopHandler.use(this)
        equipped = true
    }
}