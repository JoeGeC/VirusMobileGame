package viruswiper.game.zombie

import viruswiper.game.vector2.FloatVector2

interface ZombieDeathHandler {
    fun onZombieDeath(gold: Int, zombieHearts: Int, zombiePosition: FloatVector2)
}
