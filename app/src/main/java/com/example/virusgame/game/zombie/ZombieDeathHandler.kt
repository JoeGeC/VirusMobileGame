package com.example.virusgame.game.zombie

import com.example.virusgame.game.vector2.FloatVector2

interface ZombieDeathHandler {
    fun onZombieDeath(gold: Int, zombieHearts: Int, zombiePosition: FloatVector2)
}
