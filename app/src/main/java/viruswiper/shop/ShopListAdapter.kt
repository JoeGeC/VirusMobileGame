package viruswiper.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import viruswiper.R
import viruswiper.shop.items.ShopItem

class ShopListAdapter (val items: List<ShopItem>, internal val shopHandler: ShopHandler) : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>(){
    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false) as ConstraintLayout
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val clickListener: View.OnClickListener = ShopItemClickListener(items[position], this)
        holder.itemView.setOnClickListener(clickListener)
        holder.itemView.findViewById<ImageView>(R.id.itemImage).setImageResource(items[position].imageResource)
        holder.itemView.findViewById<TextView>(R.id.itemName).text = items[position].getName()
        holder.itemView.findViewById<TextView>(R.id.itemDescription).text = items[position].description
        holder.itemView.findViewById<TextView>(R.id.price).text = getPrice(items[position])
        holder.itemView.findViewById<ImageView>(R.id.equipped).visibility = getEquippedVisibility(items[position])
        holder.itemView.findViewById<ImageView>(R.id.itemDuller).visibility = getDullerVisibility(items[position])
    }

    private fun getPrice(shopItem: ShopItem): String {
        return shopItem.getPriceAsString()
    }

    private fun getEquippedVisibility(shopItem: ShopItem): Int {
        if(shopItem.isEquipped()) return View.VISIBLE
        return View.GONE
    }

    private fun getDullerVisibility(shopItem: ShopItem): Int {
        if(shopHandler.canPurchase(shopItem)) return View.GONE
        return View.VISIBLE
    }

    override fun getItemCount() = items.size

    fun unequipAll() {
        items.forEach { item -> item.setEquipped(false) }
    }
}