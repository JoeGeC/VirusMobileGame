package com.example.virusgame.shop

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.game.GameView
import com.example.virusgame.game.uiHandlers.ShopHandler
import com.example.virusgame.shop.items.ShopItem

class ShopFragment(private var gameView: GameView) : Fragment(), View.OnClickListener, ShopHandler {
    private lateinit var shopView: ConstraintLayout
    private lateinit var notEnoughGoldText: TextView
    private lateinit var shopRecyclerView: RecyclerView
    private lateinit var shopAdapter: ShopListAdapter
    private lateinit var shopViewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.shop, container, false)
        notEnoughGoldText = view.findViewById(R.id.notEnoughGold)
        view.findViewById<TextView>(R.id.closeShopButton).setOnClickListener(this)
        setupShop(view)
        return view
    }

    private fun setupShop(view: View) {
        shopView = view.findViewById(R.id.shop)
        shopViewManager = LinearLayoutManager(context)
        shopAdapter = ShopListAdapter(ShopList.getItems(context!!), this)
        shopRecyclerView = view.findViewById<RecyclerView>(R.id.shopList).apply {
            setHasFixedSize(true)
            layoutManager = shopViewManager
            adapter = shopAdapter
        }
        openShop()
    }

    override fun openShop() {
        gameView.openShop()
    }

    override fun purchase(shopItem: ShopItem): Boolean {
        if(gameView.purchase(shopItem)) return true
        showNotEnoughGoldText()
        return false
    }

    override fun canPurchase(shopItem: ShopItem): Boolean {
        return gameView.canPurchase(shopItem)
    }

    override fun use(shopItem: ShopItem) {
        gameView.use(shopItem)
    }

    private fun showNotEnoughGoldText() {
        notEnoughGoldText.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            notEnoughGoldText.visibility = View.GONE
        }, 1000)
    }

    override fun onClick(view: View?) {
        if(view!!.id == R.id.closeShopButton) closeShop()
    }

    override fun closeShop() {
        gameView.closeShop()
        SaveManager.saveShop(shopAdapter.items.map { it.saveData })
        fragmentManager!!.beginTransaction().remove(this).commit()
    }
}