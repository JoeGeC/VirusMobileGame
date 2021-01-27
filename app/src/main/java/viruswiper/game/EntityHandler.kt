package viruswiper.game

import viruswiper.game.player.PlayerHandler
import viruswiper.game.zombie.ZombieDamageHandler
import viruswiper.game.zombie.ZombieDeathHandler

interface EntityHandler : PlayerHandler, ZombieDeathHandler, ZombieDamageHandler {
}