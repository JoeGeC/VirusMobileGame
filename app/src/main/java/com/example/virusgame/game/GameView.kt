package com.example.virusgame.game

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.virusgame.game.swipestates.StartSwipeState
import com.example.virusgame.game.swipestates.SwipeState
import com.example.virusgame.game.zombie.Zombie
import com.example.virusgame.game.zombie.ZombieDeathHandler

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback,
    EntityHandler {
    private val thread: GameThread
    private var zombie: Zombie? = null
    private var player: Player = Player(context)
    private var sword: Sword = Sword(context)

    private var touched: Boolean = false
    private var xTouch: Int = 0
    private var yTouch: Int = 0
    private var swipeState: SwipeState = StartSwipeState()

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        zombie = Zombie(context, this, sword.offset)
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
        if(touched){
            swipeState = swipeState.onTouch(xTouch, yTouch, zombie!!)
            sword.update(xTouch.toFloat(), yTouch.toFloat())
        } else sword.active = false
        zombie!!.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        zombie!!.draw(canvas)
        player.draw(canvas)
        sword.draw(canvas)
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
        zombie = Zombie(context, this, sword.offset)
    }

    override fun inflictPlayerDamage(damage: Int) {
        player.takeDamage(damage)
    }
}

