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
import viruswiper.game.zombie.animations.InvulnerableZombieBossAnimations
import viruswiper.game.zombie.types.InvulnerableZombie
import kotlin.math.pow
import kotlin.random.Random

class InvulnerableZombieBoss(context: Context, entityHandler: EntityHandler) :
    InvulnerableZombie(context, entityHandler) {
    override val animations = InvulnerableZombieBossAnimations(context)
    private var bossPaint = Paint()
    override val healthBarOffset = 50

    init {
        bossPaint.textSize = ScreenDimensions.height / 40.0f
        bossPaint.textAlign = Paint.Align.CENTER
        bossPaint.typeface = ResourcesCompat.getFont(context, R.font.unispace)
        bossPaint.color = ContextCompat.getColor(context, R.color.white)
        bossPaint.isFakeBoldText = true
        SoundManager.playSfx(context, R.raw.zombie_boss)
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        canvas.drawText(context.getString(R.string.boss), fullRect.left + fullRect.width() / 2.0f, fullRect.top - 20.0f, bossPaint)
    }

    override fun setStats(wave: Int, playerStrength: Int){
        maxHealth = wave * 6
        currentHealth = maxHealth
        gold = maxHealth * 4
        val attackVal = wave.toFloat().pow(1.6f) + 1
        attack = Random.nextInt((attackVal * 0.8).toInt(), (attackVal * 1.2).toInt())
        attackSpeed = setAttackSpeed(playerStrength, wave)
        attackTime = setNextAttackTime()
        lastAttackTime = System.nanoTime()
        hearts = wave
    }
}