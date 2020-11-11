package com.example.virusgame.shop.items

import android.content.Context

abstract class ShopItem {
    abstract val context: Context
    abstract val imageResource: Int
    abstract val itemName: String
    abstract val description: String
    abstract val price: Int
    var bought: Boolean = false
}