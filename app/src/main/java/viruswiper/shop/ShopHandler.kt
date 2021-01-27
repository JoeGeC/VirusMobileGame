package viruswiper.shop

import viruswiper.MenuListener
import viruswiper.shop.items.ShopItem

interface ShopHandler : MenuListener {
    fun purchase(shopItem: ShopItem): Boolean
    fun canPurchase(shopItem: ShopItem): Boolean
    fun use(shopItem: ShopItem)
}
