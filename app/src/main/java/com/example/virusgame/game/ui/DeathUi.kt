package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.virusgame.R
import com.example.virusgame.game.Player
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2

class DeathUi(context: Context, screenDimensions: FloatVector2) {
    var active = true
    private val deathScreen: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.death_border)
    private val gold: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.gold)
    private val deathScreenPos = FloatVector2(screenDimensions.x / 2 - deathScreen.width / 2, screenDimensions.y / 2 - deathScreen.height / 1.7f)
    private val attackRect = Rect(
        (deathScreenPos.x + deathScreen.width / 10).toInt(),
        (deathScreenPos.y + deathScreen.height / 11.5).toInt(),
        (deathScreenPos.x + deathScreen.width / 2.3).toInt(),
        (deathScreenPos.y + deathScreen.height / 2.6).toInt()
    )
    private val healthRect = Rect(
        (deathScreenPos.x + deathScreen.width / 1.75).toInt(),
        (deathScreenPos.y + deathScreen.height / 11.5).toInt(),
        (deathScreenPos.x + deathScreen.width / 1.1f).toInt(),
        (deathScreenPos.y + deathScreen.height / 2.6f).toInt()
    )
    private val tryAgainRect = Rect(
        (deathScreenPos.x + deathScreen.width / 4.25).toInt(),
        (deathScreenPos.y + deathScreen.height / 1.3).toInt(),
        (deathScreenPos.x + deathScreen.width / 1.3).toInt(),
        (deathScreenPos.y + deathScreen.height / 1.07).toInt()
    )
    private val paint: Paint = Paint()

    init{
        paint.textAlign = Paint.Align.CENTER
        paint.color = ContextCompat.getColor(context, R.color.white)
        paint.typeface = ResourcesCompat.getFont(context, R.font.unispace)
        paint.textSize = 35.0f
    }

    fun draw(canvas: Canvas, player: Player){
        if(active){
            canvas.drawBitmap(deathScreen, deathScreenPos.x, deathScreenPos.y, null)
            canvas.drawText(player.attack.toString(), attackRect.left + attackRect.width() / 2.0f, attackRect.bottom + attackRect.height() / 1.5f, paint)
            canvas.drawText(player.maxHealth.toString(), healthRect.left + healthRect.width() / 2.0f, healthRect.bottom + healthRect.height() / 1.5f, paint)
            canvas.drawBitmap(gold, attackRect.left.toFloat(), attackRect.bottom + attackRect.height() / 1.2f, null)
            canvas.drawBitmap(gold, healthRect.left.toFloat(), healthRect.bottom + attackRect.height() / 1.2f, null)
            canvas.drawText(player.attackBuyValue.toString(), attackRect.left + (gold.width + attackRect.width()) / 2.0f, attackRect.bottom + attackRect.height().toFloat(), paint)
            canvas.drawText(player.maxHealthBuyValue.toString(), healthRect.left + (gold.width + attackRect.width()) / 2.0f, healthRect.bottom + attackRect.height().toFloat(), paint)
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
