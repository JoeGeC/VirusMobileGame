package com.example.virusgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.virusgame.game.GameView
import com.example.virusgame.settings.SettingsFragment
import com.example.virusgame.shop.ShopFragment

class GameFragment : Fragment(), View.OnClickListener {
    private lateinit var gameView: GameView
    private lateinit var menuFragmentManager: MenuFragmentManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.game, container, false)
        gameView = view.findViewById(R.id.gameView)
        view.findViewById<ImageView>(R.id.shopIcon).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.settingsIcon).setOnClickListener(this)
        menuFragmentManager = MenuFragmentManager(context!!, fragmentManager!!)
        return view
    }

    override fun onClick(view: View) {
        if(view.id == R.id.shopIcon) menuFragmentManager.openFragment(ShopFragment(gameView.gameLoop))
        if(view.id == R.id.settingsIcon) menuFragmentManager.openFragment(SettingsFragment(gameView.gameLoop))
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

    override fun onResume() {
        super.onResume()
        gameView.resume()
    }
}
