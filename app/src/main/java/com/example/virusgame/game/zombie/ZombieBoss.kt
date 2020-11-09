package com.example.virusgame.game.zombie

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.R
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.vector2.FloatVector2
import kotlin.math.pow
import kotlin.random.Random

class ZombieBoss(context: Context, entityHandler: EntityHandler, rectOffset: FloatVector2) :
    Zombie(context, entityHandler, rectOffset) {
    var bossPaint = Paint();

    init {
        bossPaint.textSize = screenHeight / 40.0f
        bossPaint.textAlign = Paint.Align.CENTER
        bossPaint.typeface = ResourcesCompat.getFont(context, R.font.unispace)
        bossPaint.color = ContextCompat.getColor(context, R.color.white)
        bossPaint.isFakeBoldText = true
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        canvas.drawText("BOSS", fullRect.left + fullRect.width() / 2.0f, fullRect.top - 20.0f, bossPaint)
    }

    override fun setStats(wave: Int, playerStrength: Int){
        maxHealth = wave * 10
        currentHealth = maxHealth
        gold = maxHealth
        val attackVal = wave.toFloat().pow(1.8f) + 1
        attack = Random.nextInt((attackVal * 0.8).toInt(), (attackVal * 1.2).toInt())
        canAttack = wave > 1
        attackSpeed = (playerStrength * 300) / wave
        setNextAttackTime()
        lastAttackTime = System.nanoTime()
    }
}