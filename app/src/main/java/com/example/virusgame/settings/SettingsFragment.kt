package com.example.virusgame.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.SoundManager
import com.example.virusgame.vibrator.VibrateManager
import kotlinx.android.synthetic.main.settings.*


class SettingsFragment(private val clearDataListener: ClearDataListener, private val settingsListener: SettingsListener?) : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpVibrateToggle()
        setUpMusicSlider()
        setUpSfxSlider()
        doneButton.setOnClickListener(this)
        clearDataButton.setOnClickListener(this)
        clearDataConfirmButton.setOnClickListener(this)
        clearDataCancelButton.setOnClickListener(this)
        settingsListener?.onMenuOpened()
    }

    private fun setUpVibrateToggle() {
        vibrationSwitch.setOnCheckedChangeListener { _, isChecked -> toggleVibration(isChecked) }
        vibrationSwitch.isChecked = VibrateManager.active
    }

    private fun toggleVibration(isChecked: Boolean) {
        VibrateManager.active = isChecked
    }

    private fun setUpMusicSlider() {
        musicPercentage.text = SoundManager.musicVolume.toString() + "%"
        musicSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

    private fun setUpSfxSlider() {
        sfxPercentage.text = SoundManager.sfxVolume.toString() + "%"
        sfxSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

    override fun onClick(view: View) {
        when (view.id) {
            R.id.doneButton -> closeSettings()
            R.id.clearDataButton -> openClearDataConfirmation()
            R.id.clearDataCancelButton -> closeClearDataConfirmation()
            R.id.clearDataConfirmButton -> clearData()
        }
    }

    private fun closeSettings() {
        settingsListener?.onMenuClosed()
        SaveManager.saveSettings()
        fragmentManager!!.beginTransaction().remove(this).commit()
    }

    private fun openClearDataConfirmation() {
        clearDataConfirmation.visibility = View.VISIBLE
    }

    private fun closeClearDataConfirmation() {
        clearDataConfirmation.visibility = View.GONE
    }

    private fun clearData() {
        SaveManager.clearData()
        clearDataListener.onDataCleared()
    }
}