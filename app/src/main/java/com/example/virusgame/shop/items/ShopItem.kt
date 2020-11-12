package com.example.virusgame.shop.items

import android.content.Context
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.zombie.ZombieDamageHandler
import com.example.virusgame.shop.ShopListAdapter

abstract class ShopItem {
    abstract val context: Context
    abstract val imageResource: Int
    abstract val description: String
    abstract val price: Int
    var saveData = ShopItemSaveData()
    abstract fun use(player: Player, zombieDamageHandler: ZombieDamageHandler)
    abstract fun isEquipped(): Boolean
    abstract fun getPriceAsString(): String
    abstract fun onClick(adapter: ShopListAdapter)

    fun setEquipped(value: Boolean){
        saveData.equipped = value
    }

    fun setBought(value: Boolean){
        saveData.bought = value
    }

    fun isBought(): Boolean {
        return saveData.bought
    }

    fun getName(): String {
        return saveData.itemName
    }
}
