package com.example.virusgame.game.uiHandlers

import com.example.virusgame.shop.items.ShopItem

interface ShopHandler {
    fun openMenu()
    fun closeMenu()
    fun purchase(shopItem: ShopItem): Boolean
    fun canPurchase(shopItem: ShopItem): Boolean
    fun use(shopItem: ShopItem)
}
