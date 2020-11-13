package com.example.virusgame.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.SoundManager
import com.example.virusgame.vibrator.VibrateManager

class SettingsFragment() : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.settings, container, false)
        setUpVibrateToggle(view)
        setUpMusicSlider(view)
        setUpSfxSlider(view)
        view.findViewById<TextView>(R.id.closeSettingsButton).setOnClickListener(this)
        view.findViewById<TextView>(R.id.doneButton).setOnClickListener(this)
        return view
    }

    private fun setUpVibrateToggle(view: View) {
        val vibrateToggle = view.findViewById<ToggleButton>(R.id.vibrationSwitch)
        vibrateToggle.setOnCheckedChangeListener { _, isChecked -> toggleVibration(isChecked) }
        vibrateToggle.isChecked = VibrateManager.active
    }

    private fun toggleVibration(isChecked: Boolean) {
        VibrateManager.active = isChecked
    }

    private fun setUpMusicSlider(view: View) {
        val musicSlider = view.findViewById<SeekBar>(R.id.musicSlider)
        val musicPercentage = view.findViewById<TextView>(R.id.musicPercentage)
        musicPercentage.text = SoundManager.musicVolume.toString() + "%"
        musicSlider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                musicPercentage.text = "$progress%"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                SoundManager.musicVolume = seekBar.progress / 100f
            }
        })
        musicSlider.progress = (SoundManager.musicVolume * 100).toInt()
    }

    private fun setUpSfxSlider(view: View) {
        val sfxSlider = view.findViewById<SeekBar>(R.id.sfxSlider)
        val sfxPercentage = view.findViewById<TextView>(R.id.sfxPercentage)
        sfxPercentage.text = SoundManager.sfxVolume.toString() + "%"
        sfxSlider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                sfxPercentage.text = "$progress%"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                SoundManager.sfxVolume = seekBar.progress / 100f
            }
        })
        sfxSlider.progress = (SoundManager.sfxVolume * 100).toInt()
    }

    override fun onClick(v: View?) {
        closeSettings()
    }

    private fun closeSettings() {
//        shopHandler.closeShop()
        SaveManager.saveSettings()
        fragmentManager!!.beginTransaction().remove(this).commit()
    }
}