package viruswiper.shop.items.abilities

import viruswiper.game.events.FirstAbilityEvent
import viruswiper.game.player.Player
import viruswiper.game.zombie.ZombieDamageHandler
import viruswiper.shop.ShopListAdapter
import viruswiper.shop.items.ShopItem

abstract class AbilityItem: ShopItem() {
    override fun use(player: Player, zombieDamageHandler: ZombieDamageHandler) {
        setEquipped(true)
        player.equipAbility(saveData.itemName, zombieDamageHandler)
        FirstAbilityEvent.trigger()
    }

    override fun isEquipped(): Boolean {
        return saveData.equipped
    }

    override fun getPriceAsString(): String {
        if(isBought()) return "0"
        return price.toString()
    }

    override fun onClick(adapter: ShopListAdapter) {
        if(isBought()) equipItem(adapter)
        else if(adapter.shopHandler.purchase(this)){
            setBought(true)
            equipItem(adapter)
        }
    }

    private fun equipItem(adapter: ShopListAdapter) {
        adapter.unequipAll()
        adapter.shopHandler.use(this)
        setEquipped(true)
    }
}