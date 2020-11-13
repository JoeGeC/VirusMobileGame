package com.example.virusgame.shop.items.abilities

import com.example.virusgame.game.abilities.AbilityFactory
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.zombie.ZombieDamageHandler
import com.example.virusgame.shop.ShopListAdapter
import com.example.virusgame.shop.items.ShopItem

abstract class AbilityItem: ShopItem() {
    override fun use(player: Player, zombieDamageHandler: ZombieDamageHandler) {
        setEquipped(true)
        player.ability = AbilityFactory().createAbility(saveData.itemName, zombieDamageHandler)
        player.ability?.playSoundEffect()
    }

    override fun isEquipped(): Boolean {
        return saveData.equipped
    }

    override fun getPriceAsString(): String {
        if(isBought()) return "0"
        return price.toString()
    }

    override fun onClick(adapter: ShopListAdapter) {
        if(isBought()) equipItem(adapter)
        else if(adapter.shopHandler.purchase(this)){
            setBought(true)
            equipItem(adapter)
        }
    }

    private fun equipItem(adapter: ShopListAdapter) {
        adapter.unequipAll()
        adapter.shopHandler.use(this)
        setEquipped(true)
    }
}