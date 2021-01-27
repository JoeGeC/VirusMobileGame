package viruswiper.shop.items.abilities

import android.content.Context
import viruswiper.R

class IceAbilityItem(override val context: Context) : AbilityItem()  {
    override val imageResource: Int = R.drawable.ice_ability_icon
    override val description: String = context.getString(R.string.deals) + " 7x " + context.getString(R.string.damage) + ". " + context.getString(R.string.cooldown) + ": 15 " + context.getString(R.string.secs)
    override val price: Int = 1000

    init {
        saveData.itemName = context.getString(R.string.iceAbility)
    }
}