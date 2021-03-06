package viruswiper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import viruswiper.death.DeathFragment
import viruswiper.death.DeathListener
import viruswiper.game.events.ShopOpensEvent
import viruswiper.settings.ClearDataListener
import viruswiper.settings.SettingsFragment
import viruswiper.speech.Speech
import viruswiper.speech.SpeechSetter
import viruswiper.tips.TipsFragment
import kotlinx.android.synthetic.main.game.*
import kotlinx.android.synthetic.main.speech.*


class GameFragment : Fragment(), View.OnClickListener, WaveListener, ClearDataListener,
    DeathListener, GamePauseListener {
    private lateinit var menuFragmentManager: MenuFragmentManager
    private lateinit var speechSetter: SpeechSetter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater.inflate(R.layout.game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuFragmentManager = MenuFragmentManager(context!!, fragmentManager!!)
        speechSetter = Speech(speech, gameView.gameLoop)
        shopIcon.setSpeechSetter(speechSetter)
        gameView.gameLoop.lateInit(speechSetter, this, this, this)
        setIconClickListeners(this)
        speech.setOnClickListener(this)
    }

    private fun setIconClickListeners(clickListener: View.OnClickListener?) {
        settingsIcon?.setOnClickListener(clickListener)
        tipsIcon?.setOnClickListener(clickListener)
        shopIcon?.setOnClickListener(clickListener)
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

    override fun gamePause() {
        Handler(Looper.getMainLooper()).postDelayed({
            pauseDimmer?.visibility = View.VISIBLE
            setIconClickListeners(null)
        }, 0)
    }

    override fun gameResume() {
        Handler(Looper.getMainLooper()).postDelayed({
            pauseDimmer?.visibility = View.GONE
            setIconClickListeners(this)
        }, 0)
    }
}
