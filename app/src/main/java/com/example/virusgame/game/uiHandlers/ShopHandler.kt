package com.example.virusgame.game.uiHandlers

import com.example.virusgame.shop.items.ShopItem

interface ShopHandler {
    fun onMenuOpened()
    fun onMenuClosed()
    fun purchase(shopItem: ShopItem): Boolean
    fun canPurchase(shopItem: ShopItem): Boolean
    fun use(shopItem: ShopItem)
}
