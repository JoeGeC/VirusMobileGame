package com.example.virusgame.game.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import com.example.virusgame.game.GameStats
import com.example.virusgame.game.Player
import com.example.virusgame.game.Vector2

class Ui (context: Context){
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private val screenDimensions = Vector2(screenWidth.toFloat(), screenHeight.toFloat())

    private val border = BorderUi(context, screenDimensions)
    private val wave = WaveUi(context, screenDimensions, border.bottom)
    private val health = HealthUi(context, screenDimensions, border.bottom)
    private val gold = GoldUi(context, screenDimensions, border.bottom)
    private val level = LevelUi(context, screenDimensions, border.bottom)

    fun draw(canvas: Canvas, player: Player, gameStats: GameStats){
        health.draw(canvas, player.currentHealth, player.maxHealth)
        border.draw(canvas)
        wave.draw(canvas, gameStats.wave)
        gold.draw(canvas, player.gold)
        level.draw(canvas, player.level)
    }
}