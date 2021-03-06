package viruswiper.game.zombie.bosses

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import viruswiper.R
import viruswiper.ScreenDimensions
import viruswiper.SoundManager
import viruswiper.game.EntityHandler
import viruswiper.game.events.FirstBossEvent
import viruswiper.game.zombie.animations.NormalZombieBossAnimations
import viruswiper.game.zombie.types.NormalZombie
import kotlin.math.pow
import kotlin.random.Random

class NormalZombieBoss(context: Context, entityHandler: EntityHandler) :
    NormalZombie(context, entityHandler) {
    override val animations = NormalZombieBossAnimations(context)
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
        hearts = wave
    }

    override fun die(){
        FirstBossEvent.completeEvent()
        super.die()
    }
}