package com.example.virusgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.virusgame.settings.ClearDataListener
import com.example.virusgame.settings.SettingsFragment
import kotlinx.android.synthetic.main.main_menu.*

class MainMenu : Fragment(), ClearDataListener{
    private lateinit var  menuFragmentManager: MenuFragmentManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater.inflate(R.layout.main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playText.setOnClickListener{ openGame() }
        settingsIcon.setOnClickListener{ openSettings() }
        creditsText.setOnClickListener{ openCredits() }
        menuFragmentManager = MenuFragmentManager(context!!, fragmentManager!!)
    }

    private fun openGame() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, GameFragment())
        transaction.commit()
    }

    private fun openSettings(){
        menuFragmentManager.openFragment(SettingsFragment(this, null))
    }

    private fun openCredits(){
        menuFragmentManager.openFragment(CreditsFragment())
    }

    override fun onDataCleared() {
        menuFragmentManager.removeFragment()
    }
}
