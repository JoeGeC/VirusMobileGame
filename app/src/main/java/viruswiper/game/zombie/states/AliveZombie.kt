package viruswiper.game.zombie.states

import android.graphics.Canvas
import viruswiper.game.zombie.types.Zombie

class AliveZombie(private var zombie: Zombie) : ZombieState(zombie) {
    override val animation = zombie.animations.idleAnimation()

    override fun draw(canvas: Canvas, x: Float, y: Float){
        super.draw(canvas, x, y)
        zombie.drawHealth(canvas)
    }

    override fun onAnimationFinish() {
        frameNum = 0
    }
}

