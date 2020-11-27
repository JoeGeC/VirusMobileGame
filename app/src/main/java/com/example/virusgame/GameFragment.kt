package com.example.virusgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.virusgame.death.DeathFragment
import com.example.virusgame.death.DeathListener
import com.example.virusgame.game.events.ShopOpensEvent
import com.example.virusgame.settings.ClearDataListener
import com.example.virusgame.settings.SettingsFragment
import com.example.virusgame.speech.Speech
import com.example.virusgame.speech.SpeechSetter
import com.example.virusgame.tips.TipsFragment
import kotlinx.android.synthetic.main.game.*
import kotlinx.android.synthetic.main.speech.*


class GameFragment : Fragment(), View.OnClickListener, WaveListener, ClearDataListener, DeathListener, ResumeListener {
    private lateinit var menuFragmentManager: MenuFragmentManager
    private lateinit var speechSetter: SpeechSetter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater.inflate(R.layout.game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuFragmentManager = MenuFragmentManager(context!!, fragmentManager!!)
        speechSetter = Speech(speech, gameView.gameLoop)
        setupShopIcon()
        setupGameView()
        settingsIcon.setOnClickListener(this)
        speech.setOnClickListener(this)
        tipsIcon.setOnClickListener(this)
    }

    private fun setupGameView() {
        gameView.gameLoop.lateInit(speechSetter, this, this, this)
    }

    private fun setupShopIcon() {
        shopIcon.setOnClickListener(this)
        shopIcon.setSpeechSetter(speechSetter)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.shopIcon -> shopIcon.onClick(menuFragmentManager, gameView.gameLoop)
            R.id.settingsIcon -> menuFragmentManager.openFragment(SettingsFragment(this, gameView.gameLoop))
            R.id.speech -> speechSetter.onClick(speech)
            R.id.tipsIcon -> menuFragmentManager.openFragment(TipsFragment(gameView.gameLoop))
        }
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

    override fun onDataCleared() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, MainMenu())
        transaction.commit()
    }

    override fun onPlayerDeath() {
        menuFragmentManager.openFragment(DeathFragment(gameView.gameLoop))
    }

    override fun canResume(): Boolean {
        return menuFragmentManager.menuIsOpen()
    }
}
