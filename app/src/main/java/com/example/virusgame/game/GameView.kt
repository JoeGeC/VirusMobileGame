package com.example.virusgame.game

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.virusgame.game.doubleSwipe.DoubleSwipeListener
import com.example.virusgame.game.uiHandlers.ShopHandler
import com.example.virusgame.shop.items.ShopItem


class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback, ShopHandler {
    private val thread: GameThread
    private val gameLoop: GameLoop = GameLoop(context)
    private val doubleSwipeListener = DoubleSwipeListener(gameLoop)

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while(retry){
            tryWaitingForThreadToDie()
            retry = false
        }
    }

    private fun tryWaitingForThreadToDie() {
        try {
            thread.setRunning(false)
            thread.join()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun update(){
        gameLoop.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        gameLoop.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gameLoop.updateTouchPos(event)
        when(event.action){
            MotionEvent.ACTION_DOWN -> gameLoop.updateTouchStartPos()
            MotionEvent.ACTION_UP -> gameLoop.releaseTouch()
            MotionEvent.ACTION_POINTER_DOWN -> doubleSwipeListener.startDoubleSwipe(event)
            MotionEvent.ACTION_POINTER_UP -> doubleSwipeListener.endDoubleSwipe()
            MotionEvent.ACTION_MOVE -> doubleSwipeListener.moveDoubleSwipe(event)
        }
        return true
    }

    override fun openShop() {
        gameLoop.openShop()
    }

    override fun closeShop() {
        gameLoop.closeShop()
    }

    override fun purchase(shopItem: ShopItem): Boolean {
        return gameLoop.purchase(shopItem)
    }

    override fun canPurchase(shopItem: ShopItem): Boolean {
        return gameLoop.canPurchase(shopItem)
    }

    override fun use(shopItem: ShopItem) {
        gameLoop.use(shopItem)
    }
}

