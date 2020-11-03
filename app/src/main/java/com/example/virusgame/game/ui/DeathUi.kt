package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import com.example.virusgame.R
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2

class DeathUi(context: Context, screenDimensions: FloatVector2) {
    var active = false
    private val deathScreen: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.death_border)
    private val deathScreenPos = FloatVector2(screenDimensions.x / 2 - deathScreen.width / 2, screenDimensions.y / 2 - deathScreen.height / 2)
    private val attackRect = Rect(
        (deathScreenPos.x + deathScreen.width / 10).toInt(),
        (deathScreenPos.y + deathScreen.height / 10).toInt(),
        (deathScreenPos.x + deathScreen.width / 2.3).toInt(),
        (deathScreenPos.y + deathScreen.height / 2.3).toInt()
    )
    private val healthRect = Rect(
        (deathScreenPos.x + deathScreen.width / 1.75).toInt(),
        (deathScreenPos.y + deathScreen.height / 10).toInt(),
        (deathScreenPos.x + deathScreen.width / 1.1f).toInt(),
        (deathScreenPos.y + deathScreen.height / 2.3f).toInt()
    )
    private val tryAgainRect = Rect(
        (deathScreenPos.x + deathScreen.width / 4.25).toInt(),
        (deathScreenPos.y + deathScreen.height / 1.3).toInt(),
        (deathScreenPos.x + deathScreen.width / 1.3).toInt(),
        (deathScreenPos.y + deathScreen.height / 1.07).toInt()
    )

    fun draw(canvas: Canvas){
        if(active){
            canvas.drawBitmap(deathScreen, deathScreenPos.x, deathScreenPos.y, null)
        }
    }

    fun hasTappedAttack(startTouch: IntVector2, endTouch: IntVector2): Boolean {
        if(active && startTouch.isInside(attackRect) && endTouch.isInside(attackRect))
            return true
        return false
    }

    fun hasTappedHealth(startTouch: IntVector2, endTouch: IntVector2): Boolean {
        if(active && startTouch.isInside(healthRect) && endTouch.isInside(healthRect))
            return true
        return false
    }

    fun hasTappedTryAgain(startTouch: IntVector2, endTouch: IntVector2): Boolean{
        if(active && startTouch.isInside(tryAgainRect) && endTouch.isInside(tryAgainRect))
            return true
        return false
    }
}
