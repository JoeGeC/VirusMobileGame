package com.example.virusgame.game

import android.graphics.Canvas
import android.view.SurfaceHolder
import com.example.virusgame.clock.Clock
import java.lang.Exception

class GameThread(private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread() {
    private var running: Boolean = false
    private val targetFps = 50
    private val targetTime = (1000 / targetFps).toLong()

    fun setRunning(isRunning: Boolean){
        this.running = isRunning
    }

    override fun run() {
        var startTime: Long
        var timePassed: Long
        var waitTime: Long
        while(running){
            startTime = System.nanoTime()
            canvas = null
            updateFrame()
            timePassed = Clock.millisecondsPassedSince(startTime)
            waitTime = targetTime - timePassed
            waitForNextFrame(waitTime)
        }
    }

    private fun updateFrame() {
        try {
            updateGameView()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            unlockCanvas()
        }
    }

    private fun updateGameView() {
        canvas = this.surfaceHolder.lockCanvas()
        synchronized(surfaceHolder) {
            this.gameView.update()
            this.gameView.draw(canvas!!)
        }
    }

    private fun unlockCanvas() {
        if (canvas == null) return
        try {
            surfaceHolder.unlockCanvasAndPost(canvas)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun waitForNextFrame(waitTime: Long) {
        try {
            sleep(waitTime)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object{
        private var canvas: Canvas? = null
    }
}