package viruswiper.game.zombie.states

import android.graphics.Bitmap
import android.graphics.Canvas
import viruswiper.game.zombie.types.Zombie

class HurtZombie(private var zombie: Zombie) : ZombieState(zombie) {
    override val animation: List<Bitmap> = zombie.animations.hurtAnimation()
    override val animationSpeed = 20

    override fun draw(canvas: Canvas, x: Float, y: Float) {
        super.draw(canvas, x, y)
        zombie.drawHealth(canvas)
    }

    override fun onAnimationFinish() {
        zombie.state = AliveZombie(zombie)
    }
}