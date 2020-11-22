package com.example.virusgame.game.zombie.types

import android.content.Context
import com.example.virusgame.ScreenDimensions
import com.example.virusgame.game.EntityHandler
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.animations.NormalZombieAnimations
import com.example.virusgame.game.zombie.animations.ZombieAnimations
import com.example.virusgame.game.zombie.states.AliveZombie
import com.example.virusgame.game.zombie.states.ZombieState

open class NormalZombie(context: Context, entityHandler: EntityHandler) : Zombie(context, entityHandler){
    override val animations: ZombieAnimations = NormalZombieAnimations(context)
}