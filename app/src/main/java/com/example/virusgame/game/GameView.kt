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
import com.example.virusgame.game.zombie.ZombieDamageCalculator
import com.example.virusgame.game.zombie.ZombieMaker

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback,
    EntityHandler {
    private val thread: GameThread
    private val ui: Ui = Ui(context)
    private val speech: Speech = Speech(context)
    private var zombie: Zombie? = null
    private var player: Player = Player(context)
    private var sword: Sword = Sword(context)
    private var wave: Int = 1
    private var zombieKillCount = 0

    private var touched: Boolean = false
    private var xTouch: Int = 0
    private var yTouch: Int = 0
    private var xStartTouch: Int = 0
    private var yStartTouch: Int = 0
    private var swipeState: SwipeState = StartSwipeState()

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
        ZombieDamageCalculator.player = player
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        spawnNewZombie()
        thread.setRunning(true)
        thread.start()
        speech.splitTextForSpeech("Blah blah")
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
        sword.draw(canvas)
        ui.drawHealth(canvas, player.health, player.maxHealth)
        ui.drawBorder(canvas)
        ui.drawWave(canvas, wave)
        ui.drawGold(canvas, player.gold)
        ui.drawLevel(canvas, player.level)
        speech.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        xTouch = event.x.toInt()
        yTouch = event.y.toInt()

        when(event.action){
            MotionEvent.ACTION_DOWN -> updateTouchStartPos()
            MotionEvent.ACTION_MOVE -> touched = true
            MotionEvent.ACTION_UP -> releaseTouch()
        }
        return true
    }

    private fun updateTouchStartPos() {
        xStartTouch = xTouch
        yStartTouch = yTouch
        touched = true
    }

    private fun releaseTouch() {
        touched = false
        swipeState = StartSwipeState()
        speech.onTouch(xStartTouch, yStartTouch)
    }

    override fun takeGold(gold: Int) {
        player.increaseGold(gold)
    }

    override fun takeExp(exp: Int){
        player.earnExp(exp)
    }

    override fun spawnNewZombie() {
        zombie = ZombieMaker().makeZombie(context, this, sword.offset, wave)
    }

    override fun incrementZombieKillCount() {
        zombieKillCount++
        if(zombieKillCount >= 10){
            wave++
            zombieKillCount = 0
        }
    }

    override fun inflictPlayerDamage(damage: Int) {
        player.takeDamage(damage)
    }
}

