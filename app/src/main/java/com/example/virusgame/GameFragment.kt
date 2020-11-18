package com.example.virusgame

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.virusgame.game.events.ShopOpensEvent
import com.example.virusgame.settings.ClearDataListener
import com.example.virusgame.settings.SettingsFragment
import kotlinx.android.synthetic.main.game.*
import kotlinx.android.synthetic.main.speech.*


class GameFragment : Fragment(), View.OnClickListener, WaveListener, ClearDataListener, SpeechSetter {
    private lateinit var menuFragmentManager: MenuFragmentManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater.inflate(R.layout.game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupShopIcon()
        setupGameView()
        settingsIcon.setOnClickListener(this)
        speech.setOnClickListener(this)
        menuFragmentManager = MenuFragmentManager(context!!, fragmentManager!!)
    }

    private fun setupGameView() {
        gameView.gameLoop.lateInit(this, this)
    }

    private fun setupShopIcon() {
        shopIcon.setOnClickListener(this)
        shopIcon.setSpeechSetter(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.shopIcon -> shopIcon.onClick(menuFragmentManager, gameView.gameLoop)
            R.id.settingsIcon -> menuFragmentManager.openFragment(SettingsFragment(this, gameView.gameLoop))
            R.id.speech -> speech.visibility = View.GONE
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

    override fun setMessage(messageToSet: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            message.text = messageToSet
            speech.visibility = View.VISIBLE
            bounceMessage()
        }, 0)
    }

    private fun bounceMessage() {
        val upAnimation = ObjectAnimator.ofFloat(speech, "translationY", -100f)
        val downAnimation = ObjectAnimator.ofFloat(speech, "translationY", 0f)
        Handler(Looper.getMainLooper()).postDelayed({
            AnimatorSet().apply {
                play(upAnimation).before(downAnimation)
                start()
            }
        }, 0)
    }
}
