package com.example.virusgame.death

interface DeathUiHandler {
    fun updateAttackValues(newAttackVal: Int, newAttackCost: Int)
    fun updateHealthValues(newHealthVal: Int, newHealthCost: Int)
}
