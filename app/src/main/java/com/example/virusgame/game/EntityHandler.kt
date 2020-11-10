package com.example.virusgame.game

import com.example.virusgame.game.player.PlayerHandler
import com.example.virusgame.game.zombie.ZombieDamageHandler
import com.example.virusgame.game.zombie.ZombieDeathHandler

interface EntityHandler : PlayerHandler, ZombieDeathHandler, ZombieDamageHandler {
}