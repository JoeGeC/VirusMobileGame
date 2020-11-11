package com.example.virusgame.shop.items

import android.content.Context
import com.example.virusgame.R

class FireAbilityItem(override val context: Context) : ShopItem() {
    override val imageResource: Int = R.drawable.fireball_ability_icon
    override val itemName: String = context.getString(R.string.fireAbility)
    override val description: String = "Deals double damage. Cooldown: 10 secs."
    override val price: Int = 200
}