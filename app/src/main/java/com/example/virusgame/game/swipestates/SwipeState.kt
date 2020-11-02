package com.example.virusgame.game.swipestates

import com.example.virusgame.game.SwipeTaker
import com.example.virusgame.game.vector2.IntVector2

interface SwipeState {
    fun onTouch(touchPos: IntVector2, swipeTaker: SwipeTaker) : SwipeState
}