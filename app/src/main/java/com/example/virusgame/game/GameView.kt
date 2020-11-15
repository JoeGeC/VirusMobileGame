package com.example.virusgame.game

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.virusgame.game.doubleSwipe.DoubleSwipeManager


class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private lateinit var thread: GameThread
    val gameLoop: GameLoop = GameLoop(context)
    private val doubleSwipeManager = DoubleSwipeManager(gameLoop)

    init {
        holder.addCallback(this)
        newGameThread()
    }

    private fun newGameThread(){
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
            MotionEvent.ACTION_POINTER_DOWN -> doubleSwipeManager.startDoubleSwipe(event)
            MotionEvent.ACTION_POINTER_UP -> doubleSwipeManager.endDoubleSwipe()
            MotionEvent.ACTION_MOVE -> doubleSwipeManager.moveDoubleSwipe(event)
        }
        return true
    }

    fun pause() {
        gameLoop.pause()
    }

    fun resume(){
        newGameThread()
        gameLoop.resume()
    }
}

