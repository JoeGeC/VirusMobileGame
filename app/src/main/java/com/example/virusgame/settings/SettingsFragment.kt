package com.example.virusgame.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.example.virusgame.R

class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.settings, container, false)
        view.findViewById<SwitchCompat>(R.id.vibrationSwitch).setOnCheckedChangeListener { _, isChecked -> toggleVibration(isChecked) }
        return view
    }

    private fun toggleVibration(isChecked: Boolean) {

    }
}