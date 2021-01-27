package viruswiper.shop.items.healthPotions

import viruswiper.game.player.Player
import viruswiper.game.zombie.ZombieDamageHandler
import viruswiper.shop.ShopListAdapter
import viruswiper.shop.items.ShopItem

abstract class HealthItem : ShopItem() {
    abstract val effect: Int

    override fun use(player: Player, zombieDamageHandler: ZombieDamageHandler) {
        player.restoreHealth(effect)
    }

    override fun isEquipped(): Boolean {
        return false
    }

    override fun getPriceAsString(): String {
        return price.toString()
    }

    override fun onClick(adapter: ShopListAdapter) {
        if(adapter.shopHandler.purchase(this))
            adapter.shopHandler.use(this)
    }
}
