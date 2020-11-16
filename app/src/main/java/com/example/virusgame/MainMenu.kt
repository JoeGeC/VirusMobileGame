package com.example.virusgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.virusgame.settings.ClearDataListener
import com.example.virusgame.settings.SettingsFragment

class MainMenu : Fragment(), ClearDataListener{
    private lateinit var  menuFragmentManager: MenuFragmentManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.main_menu, container, false)
        view.findViewById<TextView>(R.id.playText).setOnClickListener{ openGame() }
        view.findViewById<LinearLayout>(R.id.settingsIcon).setOnClickListener{ openSettings() }
        menuFragmentManager = MenuFragmentManager(context!!, fragmentManager!!)
        return view
    }

    private fun openGame() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, GameFragment())
        transaction.commit()
    }

    private fun openSettings(){
        menuFragmentManager.openFragment(SettingsFragment(this, null))
    }

    override fun onDataCleared() {
        menuFragmentManager.removeFragment()
    }
}
