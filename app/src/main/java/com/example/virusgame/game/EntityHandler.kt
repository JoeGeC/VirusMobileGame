package com.example.virusgame.game

import com.example.virusgame.game.zombie.ZombieDeathHandler

interface EntityHandler : PlayerHandler, ZombieDeathHandler {
}