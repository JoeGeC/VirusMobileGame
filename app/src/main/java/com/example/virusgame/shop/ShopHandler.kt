package com.example.virusgame.shop

import com.example.virusgame.MenuListener
import com.example.virusgame.shop.items.ShopItem

interface ShopHandler : MenuListener {
    fun purchase(shopItem: ShopItem): Boolean
    fun canPurchase(shopItem: ShopItem): Boolean
    fun use(shopItem: ShopItem)
}
