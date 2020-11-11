package com.example.virusgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.virusgame.game.GameView
import com.example.virusgame.game.uiHandlers.ShopHandler
import com.example.virusgame.shop.ShopListAdapter
import com.example.virusgame.shop.items.FireAbility
import com.example.virusgame.shop.items.ShopItem

class GameFragment : Fragment(), ShopHandler {
    private lateinit var shopView: LinearLayout
    private lateinit var shopRecyclerView: RecyclerView
    private lateinit var shopAdapter: RecyclerView.Adapter<*>
    private lateinit var shopViewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.game, container, false)
        setupShop(view)
        return view
    }

    private fun setupShop(view: View) {
        view.findViewById<GameView>(R.id.gameView).shopHandler = this
        shopView = view.findViewById(R.id.shop)
        shopViewManager = LinearLayoutManager(context)
        var items : Array<ShopItem> = arrayOf(FireAbility(context!!))
        shopAdapter = ShopListAdapter(items)
        shopRecyclerView = view.findViewById<RecyclerView>(R.id.shopList).apply {
            setHasFixedSize(true)
            layoutManager = shopViewManager
            adapter = shopAdapter
        }
    }

    override fun openShop() {
        shopView.visibility = View.VISIBLE
    }
}
