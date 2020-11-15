package com.example.virusgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.virusgame.game.GameView
import com.example.virusgame.game.events.ShopOpensEvent
import com.example.virusgame.settings.SettingsFragment
import com.example.virusgame.shop.ShopFragment


class GameFragment : Fragment(), View.OnClickListener, WaveListener {
    private lateinit var gameView: GameView
    private lateinit var menuFragmentManager: MenuFragmentManager
    private lateinit var shopIcon: ShopIcon

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.game, container, false)
        setupShopIcon(view)
        setupGameView(view)
        view.findViewById<ImageView>(R.id.settingsIcon).setOnClickListener(this)
        menuFragmentManager = MenuFragmentManager(context!!, fragmentManager!!)
        return view
    }

    private fun setupGameView(view: View) {
        gameView = view.findViewById(R.id.gameView)
        gameView.gameLoop.assignWaveListener(this)
    }

    private fun setupShopIcon(view: View) {
        shopIcon = view.findViewById(R.id.shopIcon)
        shopIcon.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.shopIcon) shopIcon.onClick(menuFragmentManager, gameView.gameLoop)
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

    override fun onWaveChange(wave: Int) {
        if(wave >= 3) {
            shopIcon.setAvailable(activity!!)
            ShopOpensEvent.trigger()
        } else shopIcon.setUnavailable(activity!!)
    }
}
