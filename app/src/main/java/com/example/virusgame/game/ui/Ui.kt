package com.example.virusgame.game.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import com.example.virusgame.game.GameStats
import com.example.virusgame.game.Player
import com.example.virusgame.game.PlayerHandler
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2

class Ui (context: Context){
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private val screenDimensions = FloatVector2(screenWidth.toFloat(), screenHeight.toFloat())

    private val border = BorderUi(context, screenDimensions)
    private val wave = WaveUi(context, screenDimensions, border.bottom)
    private val health = HealthUi(context, screenDimensions, border.bottom)
    private val gold = GoldUi(context, screenDimensions, border.bottom)
    val death = DeathUi(context, screenDimensions)

    fun draw(canvas: Canvas, player: Player, gameStats: GameStats){
        border.draw(canvas)
        wave.draw(canvas, gameStats.wave)
        gold.draw(canvas, player.gold)
        health.draw(canvas, player.currentHealth, player.maxHealth)
        death.draw(canvas)
    }

    fun onTouch(startTouchPos: IntVector2, touchPos: IntVector2, deathHandler: DeathHandler) {
        if(death.hasTappedAttack(startTouchPos, touchPos)) deathHandler.increaseAttack()
        if(death.hasTappedHealth(startTouchPos, touchPos)) deathHandler.increaseHealth()
        if(death.hasTappedTryAgain(startTouchPos, touchPos)) deathHandler.revive()
    }
}