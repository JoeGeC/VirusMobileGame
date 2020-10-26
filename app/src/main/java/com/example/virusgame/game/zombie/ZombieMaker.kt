package com.example.virusgame.game.zombie

import android.content.Context
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.Vector2

class ZombieMaker {
    fun makeZombie(context: Context, entityHandler: EntityHandler, offset: Vector2, level: Int): Zombie? {
        val zombie = Zombie(context, entityHandler, offset)
        zombie.setStats(level)
        return zombie
    }
}
