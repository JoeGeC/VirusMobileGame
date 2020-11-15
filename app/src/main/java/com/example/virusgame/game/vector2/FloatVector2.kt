package com.example.virusgame.game.vector2

class FloatVector2(var x: Float, var y: Float) {
    fun offsetX(offset: Int): FloatVector2 {
        return FloatVector2(x + offset, y)
    }
}