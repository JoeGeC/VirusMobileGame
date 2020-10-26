package com.example.virusgame.game

import android.content.Context
import com.example.virusgame.game.zombie.Zombie

class ZombieMaker {
    fun makeZombie(context: Context, entityHandler: EntityHandler, offset: Vector2, level: Int): Zombie? {
        val zombie = Zombie(context, entityHandler, offset)
        zombie.setStats(level)
        return zombie
    }
}
