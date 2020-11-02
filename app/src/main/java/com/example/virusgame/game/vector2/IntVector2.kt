package com.example.virusgame.game.vector2

import android.graphics.Rect

class IntVector2(var x: Int, var y: Int) {
    fun toFloat(): FloatVector2{
        return FloatVector2(x.toFloat(), y.toFloat())
    }

    fun isInside(rect: Rect): Boolean{
        if(x > rect.left && x < rect.right && y > rect.top && y < rect.bottom)
            return true
        return false
    }

    fun isOutside(rect: Rect): Boolean{
        if(x > rect.right || x < rect.left || y < rect.top || y > rect.bottom)
            return true
        return false
    }
}