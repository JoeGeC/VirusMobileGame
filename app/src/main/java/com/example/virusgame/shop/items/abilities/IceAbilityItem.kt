package com.example.virusgame.shop.items.abilities

import android.content.Context
import com.example.virusgame.R

class IceAbilityItem(override val context: Context) : AbilityItem()  {
    override val imageResource: Int = R.drawable.ice_ability_icon
    override val itemName: String = context.getString(R.string.iceAbility)
    override val description: String = "Deals 7x damage. Cooldown: 15 secs."
    override val price: Int = 1000
}