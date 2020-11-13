package com.example.virusgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.virusgame.game.GameView
import com.example.virusgame.shop.ShopFragment

class GameFragment : Fragment(), View.OnClickListener {
    private lateinit var gameView: GameView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.game, container, false)
        gameView = view.findViewById(R.id.gameView)
        view.findViewById<ImageView>(R.id.shopIcon).setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {
        if(view.id == R.id.shopIcon && !shopIsOpen()) openShop()
    }

    private fun shopIsOpen(): Boolean {
        return fragmentManager!!.findFragmentByTag(getString(R.string.shop)) != null
    }

    private fun openShop(){
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.add(R.id.subFragmentContainer, ShopFragment(gameView), getString(R.string.shop))
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
        gameView.newGameThread()
    }
}
