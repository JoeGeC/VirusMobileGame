package com.example.virusgame.game.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import com.example.virusgame.R
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2

class ShopUi(context: Context, screenDimensions: FloatVector2, borderBottom: Float) {
    private val shopIcon: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.shop)
    private val shopIconPos = FloatVector2(screenDimensions.x / 4 - shopIcon.width / 2, borderBottom - borderBottom / 5 - shopIcon.height)
    private val rect = Rect(
        shopIconPos.x.toInt(),
        shopIconPos.y.toInt(),
        (shopIconPos.x + shopIcon.width).toInt(),
        (shopIconPos.y + shopIcon.height).toInt()
    )

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(shopIcon, shopIconPos.x, shopIconPos.y, null)
    }

    fun tapped(startTouchPos: IntVector2, touchPos: IntVector2): Boolean {
        return (startTouchPos.isInside(rect) && touchPos.isInside(rect))
    }
}
