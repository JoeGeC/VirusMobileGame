package com.example.virusgame.game.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import com.example.virusgame.game.*
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.uiHandlers.UiHandler
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
    internal val death = DeathUi(context, screenDimensions)
    private val shop = ShopUi(context, screenDimensions, border.bottom)
    private val ability = AbilityUi(context, screenDimensions, border.bottom)

    fun draw(canvas: Canvas, player: Player, gameStats: GameStats){
        shop.draw(canvas)
        border.draw(canvas)
        wave.draw(canvas, gameStats.wave)
        gold.draw(canvas, player.gold)
        health.draw(canvas, player.currentHealth, player.maxHealth)
        ability.draw(canvas, player.ability!!)
        death.draw(canvas, player)
    }

    fun onTouch(startTouchPos: IntVector2, touchPos: IntVector2, uiHandler: UiHandler) {
        if(death.hasTappedAttack(startTouchPos, touchPos)) uiHandler.upgradeAttack()
        if(death.hasTappedHealth(startTouchPos, touchPos)) uiHandler.upgradeHealth()
        if(death.hasTappedTryAgain(startTouchPos, touchPos)) uiHandler.revive()
        if(shop.tapped(startTouchPos, touchPos)) uiHandler.openShop()
    }
}