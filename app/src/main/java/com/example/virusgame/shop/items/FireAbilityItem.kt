package com.example.virusgame.shop.items

import android.content.Context
import com.example.virusgame.R
import com.example.virusgame.game.abilities.AbilityFactory
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.zombie.ZombieDamageHandler

class FireAbilityItem(override val context: Context) : ShopItem() {
    override val imageResource: Int = R.drawable.fireball_ability_icon
    override val itemName: String = context.getString(R.string.fireAbility)
    override val description: String = "Deals double damage. Cooldown: 10 secs."
    override val price: Int = 200

    override fun use(player: Player, zombieDamageHandler: ZombieDamageHandler) {
        player.ability = AbilityFactory().createAbility(itemName, zombieDamageHandler)
    }
}