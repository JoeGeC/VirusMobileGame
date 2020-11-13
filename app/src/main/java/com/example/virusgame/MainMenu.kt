package com.example.virusgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.main_menu.*

class MainMenu : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        val view = inflater.inflate(R.layout.main_menu, container, false)
        view.findViewById<TextView>(R.id.playText).setOnClickListener{ openGame() }
        return view
    }

    private fun openGame() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, GameFragment())
        transaction.commit()
    }

    private fun openSettings(){

    }
}
