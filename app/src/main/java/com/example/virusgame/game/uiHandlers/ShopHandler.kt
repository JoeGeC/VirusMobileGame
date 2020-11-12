package com.example.virusgame.game.uiHandlers

import com.example.virusgame.shop.items.ShopItem

interface ShopHandler {
    fun openShop()
    fun closeShop()
    fun purchase(shopItem: ShopItem): Boolean
    fun use(shopItem: ShopItem)
}
