package com.example.virusgame.shop.items

import android.content.Context
import com.example.virusgame.R

class FireAbility(
    override val context: Context,
    override val imageResource: Int = R.drawable.fireball_ability_icon,
    override val itemName: String = "Fire attack",
    override val description: String = "Deals double damage. Cooldown: 10 secs.",
    override val price: Int = 200
) : ShopItem() {

}