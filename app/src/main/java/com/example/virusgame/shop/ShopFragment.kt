package com.example.virusgame.shop

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.game.events.ShopOpensEvent
import com.example.virusgame.game.uiHandlers.ShopHandler
import com.example.virusgame.shop.items.ShopItem
import kotlinx.android.synthetic.main.shop.*

class ShopFragment(private var shopHandler: ShopHandler) : Fragment(), View.OnClickListener, ShopHandler {
    private lateinit var shopAdapter: ShopListAdapter
    private lateinit var shopViewManager: RecyclerView.LayoutManager

    init {
        ShopOpensEvent.complete()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doneButton.setOnClickListener(this)
        setupShop()
    }

    private fun setupShop() {
        shopViewManager = LinearLayoutManager(context)
        shopAdapter = ShopListAdapter(ShopList.getItems(context!!), this)
        shopList.apply {
            setHasFixedSize(true)
            layoutManager = shopViewManager
            adapter = shopAdapter
        }
        onMenuOpened()
    }

    override fun onMenuOpened() {
        shopHandler.onMenuOpened()
    }

    override fun purchase(shopItem: ShopItem): Boolean {
        if(shopHandler.purchase(shopItem)) return true
        showNotEnoughGoldText()
        return false
    }

    override fun canPurchase(shopItem: ShopItem): Boolean {
        return shopHandler.canPurchase(shopItem)
    }

    override fun use(shopItem: ShopItem) {
        shopHandler.use(shopItem)
    }

    private fun showNotEnoughGoldText() {
        notEnoughGold.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            notEnoughGold.visibility = View.GONE
        }, 1000)
    }

    override fun onClick(view: View?) {
        if(view!!.id == R.id.doneButton) onMenuClosed()
    }

    override fun onMenuClosed() {
        shopHandler.onMenuClosed()
        SaveManager.saveShop(shopAdapter.items.map { it.saveData })
        fragmentManager!!.beginTransaction().remove(this).commit()
    }
}