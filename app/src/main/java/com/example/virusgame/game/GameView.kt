package com.example.virusgame.game

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.game.swipestates.StartSwipeState
import com.example.virusgame.game.swipestates.SwipeState

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback, ZombieDeathHandler {
    private val thread: GameThread
    private var zombie: Zombie? = null
    private var player: Player = Player(context)

    private var touched: Boolean = false
    private var xTouch: Int = 0
    private var yTouch: Int = 0
    private var swipeState: SwipeState = StartSwipeState()

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        zombie = Zombie(context, this)
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
        if(touched) swipeState = swipeState.onTouch(xTouch, yTouch, zombie!!)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        zombie!!.draw(canvas)
        player.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        xTouch = event.x.toInt()
        yTouch = event.y.toInt()
        when(event.action){
            MotionEvent.ACTION_DOWN -> touched = true
            MotionEvent.ACTION_MOVE -> touched = true
            MotionEvent.ACTION_UP -> releaseTouch()
            MotionEvent.ACTION_CANCEL -> releaseTouch()
            MotionEvent.ACTION_OUTSIDE -> releaseTouch()
        }
        return true
    }

    private fun releaseTouch() {
        touched = false
        swipeState = StartSwipeState()
    }

    override fun takeGold(gold: Int) {
        player.increaseGold(gold)
    }

    override fun spawnNewZombie() {
        zombie = Zombie(context, this)
    }
}

