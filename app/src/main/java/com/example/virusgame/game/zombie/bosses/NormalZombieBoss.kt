package com.example.virusgame.game.zombie.bosses

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.R
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.SoundManager
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.events.FirstBossEvent
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.animations.NormalZombieAnimations
import com.example.virusgame.game.zombie.states.AliveZombie
import com.example.virusgame.game.zombie.states.ZombieState
import com.example.virusgame.game.zombie.types.NormalZombie
import kotlin.math.pow
import kotlin.random.Random

class NormalZombieBoss(context: Context, entityHandler: EntityHandler, rectOffset: IntVector2) :
    NormalZombie(context, entityHandler, rectOffset) {
    private var bossPaint = Paint()
    override val healthBarOffset = 50

    init {
        bossPaint.textSize = ScreenDimensions.height / 40.0f
        bossPaint.textAlign = Paint.Align.CENTER
        bossPaint.typeface = ResourcesCompat.getFont(context, R.font.unispace)
        bossPaint.color = ContextCompat.getColor(context, R.color.white)
        bossPaint.isFakeBoldText = true
        SoundManager.playSfx(context, R.raw.zombie_boss)
        FirstBossEvent.trigger()
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        canvas.drawText(context.getString(R.string.boss), fullRect.left + fullRect.width() / 2.0f, fullRect.top - 20.0f, bossPaint)
    }

    override fun setStats(wave: Int, playerStrength: Int){
        maxHealth = wave * 10
        currentHealth = maxHealth
        gold = maxHealth
        val attackVal = wave.toFloat().pow(1.8f) + 1
        attack = Random.nextInt((attackVal * 0.8).toInt(), (attackVal * 1.2).toInt())
        attackSpeed = (playerStrength * 300) / wave
        attackTime = setNextAttackTime()
        lastAttackTime = System.nanoTime()
        hearts = 1 + wave / 5
    }

    override fun die(){
        FirstBossEvent.onComplete()
        super.die()
    }
}