package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.Canvas
import com.example.virusgame.game.*
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.vector2.IntVector2

class Ui (context: Context){
    private val border = BorderUi(context)
    private val wave = WaveUi(context, border.bottom)
    private val health = HealthUi(context, border.bottom)
    private val gold = GoldUi(context, border.bottom)
    private val zombieHeart = ZombieHeartUi(context, border.bottom)
    internal val death = DeathUi(context)
    private val ability = AbilityUi(border.bottom)

    fun draw(canvas: Canvas, player: Player, gameStats: GameStats){
        border.draw(canvas)
        wave.draw(canvas, gameStats.getCurrentWave())
        health.draw(canvas, player.currentHealth, player.maxHealth)
        gold.draw(canvas, player.gold)
        zombieHeart.draw(canvas, player.zombieHearts)
        ability.draw(canvas, player.ability)
        death.draw(canvas, player)
    }

    fun onTouch(startTouchPos: IntVector2, touchPos: IntVector2) {
//        if(death.hasTappedAttack(startTouchPos, touchPos)) uiHandler.upgradeAttack()
//        if(death.hasTappedHealth(startTouchPos, touchPos)) uiHandler.upgradeHealth()
//        if(death.hasTappedTryAgain(startTouchPos, touchPos)) uiHandler.revive()
    }
}