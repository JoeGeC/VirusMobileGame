package com.example.virusgame

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread
    private var zombie: Zombie? = null
    var uiManager: UiManager? = null

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        zombie = Zombie(BitmapFactory.decodeResource(resources, R.drawable.zombie))

        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while(retry){
            try{
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception){
                e.printStackTrace()
            }

            retry = false
        }
    }

    fun update(){
        uiManager!!.updateHealth(zombie!!.health)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        zombie!!.draw(canvas)
    }
}
