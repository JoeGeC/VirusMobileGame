package com.example.virusgame.game.uiHandlers

import com.example.virusgame.shop.items.ShopItem

interface ShopHandler {
    fun openShop()
    fun purchase(shopItem: ShopItem): Boolean
}
