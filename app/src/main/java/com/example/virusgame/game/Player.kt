package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.virusgame.R

class Player(var context: Context){
    private var screenWidth = Resources.getSystem().displayMetrics.widthPixels
    var gold = 0
    var health = 10
    private var exp = 0
    private var level = 1
    public var attack = 1
    private val goldPaint: Paint = Paint()
    private val healthPaint: Paint = Paint()

    init {
        setPaintParams(goldPaint,50.0f, R.color.gold)
        setPaintParams(healthPaint, 50.0f, R.color.green)
    }

    private fun setPaintParams(paint: Paint, textSize: Float, colorResource: Int) {
        paint.textSize = textSize
        paint.color = ContextCompat.getColor(context, colorResource)
    }

    fun draw(canvas: Canvas){
        canvas.drawText("Gold: $gold", screenWidth - 250.0f, 150.0f, goldPaint)
        canvas.drawText("Health: $health", screenWidth - 250.0f, 100.0f, healthPaint)
    }

    fun increaseGold(goldToAdd: Int){
        gold += goldToAdd
    }

    fun takeDamage(damage: Int){
        health -= damage
        if(health <= 0){
            health = 0
            die()
        }
    }

    private fun die(){

    }

    fun earnExp(expEarned: Int) {
        exp += expEarned
        while(expEarned >= level * 50)
            levelUp()
    }

    private fun levelUp() {
        health = (10 + level * 1.5).toInt()
        attack = (level * 1.5).toInt()
    }
}