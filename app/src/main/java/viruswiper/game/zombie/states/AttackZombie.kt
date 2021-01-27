package viruswiper.game.zombie.states

import android.graphics.Bitmap
import android.graphics.Canvas
import viruswiper.R
import viruswiper.SoundManager
import viruswiper.game.events.ZombieAttackEvent
import viruswiper.game.zombie.types.Zombie

class AttackZombie(private val zombie: Zombie) : ZombieState(zombie) {
    override val animation: List<Bitmap> = zombie.animations.attackAnimation()

    override fun draw(canvas: Canvas, x: Float, y: Float) {
        super.draw(canvas, x, y)
        zombie.drawHealth(canvas)
    }

    override fun onAnimationFinish() {
        attack()
    }

    private fun attack() {
        zombie.entityHandler.inflictPlayerDamage(zombie.attack)
        ZombieAttackEvent.onFail()
        SoundManager.playSfx(zombie.context, R.raw.damage)
        zombie.state = AliveZombie(zombie)
    }

    override fun onSuccessfulSwipe() { }

    override fun update() { }
}
