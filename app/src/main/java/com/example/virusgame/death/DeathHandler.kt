package com.example.virusgame.death

import com.example.virusgame.MenuListener


interface DeathHandler : MenuListener {
    fun upgradeAttack()
    fun upgradeHealth()
    fun revive()
    fun setDeathUiHandler(handler: DeathUiHandler)

}