package viruswiper.shop.items.abilities

import android.content.Context
import viruswiper.R

class LightningAbilityItem(override val context: Context) : AbilityItem()  {
    override val imageResource: Int = R.drawable.lightning_ability_icon
    override val description: String = context.getString(R.string.deals) + " 3x " + context.getString(R.string.damage) + ". " + context.getString(R.string.cooldown) + ": 5 " + context.getString(R.string.secs)
    override val price: Int = 500

    init {
        saveData.itemName = context.getString(R.string.lightningAbility)
    }
}