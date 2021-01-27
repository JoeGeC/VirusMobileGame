package viruswiper.shop.items.abilities

import android.content.Context
import viruswiper.R

class FireAbilityItem(override val context: Context) : AbilityItem() {
    override val imageResource: Int = R.drawable.fireball_ability_icon
    override val description: String = context.getString(R.string.deals) + " 4x " + context.getString(R.string.damage) + ". " + context.getString(R.string.cooldown) + ": 10 " + context.getString(R.string.secs)
    override val price: Int = 200

    init {
        saveData.itemName = context.getString(R.string.fireAbility)
    }
}