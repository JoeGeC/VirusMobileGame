package com.example.virusgame.death

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.virusgame.R
import kotlinx.android.synthetic.main.death_screen.*

class DeathFragment(private val deathHandler: DeathHandler) : Fragment(), View.OnClickListener,
    DeathUiHandler {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.death_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deathHandler.setDeathUiHandler(this)
        deathHandler.onMenuOpened()
        swordIcon.setOnClickListener(this)
        healthIcon.setOnClickListener(this)
        tryAgainButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.swordIcon -> deathHandler.upgradeAttack()
            R.id.healthIcon -> deathHandler.upgradeHealth()
            R.id.tryAgainButton -> revive()
        }
    }

    override fun updateAttackValues(newAttackVal: Int, newAttackCost: Int) {
        attackVal.text = newAttackVal.toString()
        attackCost.text = newAttackCost.toString()
    }

    override fun updateHealthValues(newHealthVal: Int, newHealthCost: Int) {
        healthVal.text = newHealthVal.toString()
        healthCost.text = newHealthCost.toString()
    }

    private fun revive(){
        deathHandler.revive()
        deathHandler.onMenuClosed()
        fragmentManager!!.beginTransaction().remove(this).commit()
    }
}