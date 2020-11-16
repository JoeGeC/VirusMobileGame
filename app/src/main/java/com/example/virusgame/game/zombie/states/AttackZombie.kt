package com.example.virusgame.game.zombie.states

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.virusgame.R
import com.example.virusgame.SoundManager
import com.example.virusgame.game.events.ZombieAttackEvent
import com.example.virusgame.game.zombie.types.Zombie
import com.example.virusgame.game.zombie.animations.NormalZombieAnimations

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
