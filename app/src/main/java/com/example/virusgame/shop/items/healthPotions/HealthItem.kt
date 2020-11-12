package com.example.virusgame.shop.items.healthPotions

import com.example.virusgame.game.player.Player
import com.example.virusgame.game.zombie.ZombieDamageHandler
import com.example.virusgame.shop.ShopListAdapter
import com.example.virusgame.shop.items.ShopItem

abstract class HealthItem : ShopItem() {
    abstract val effect: Int

    override fun use(player: Player, zombieDamageHandler: ZombieDamageHandler) {
        player.restoreHealth(effect)
    }

    override fun isEquipped(): Boolean {
        return false
    }

    override fun getPriceAsString(): String {
        return price.toString()
    }

    override fun onClick(adapter: ShopListAdapter) {
        if(adapter.shopHandler.purchase(this))
            adapter.shopHandler.use(this)
    }
}
