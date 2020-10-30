package com.example.virusgame.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.decodeResource
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.game.events.EventManager
import com.example.virusgame.game.events.FirstTimePlayingEvent
import com.example.virusgame.game.swipestates.StartSwipeState
import com.example.virusgame.game.swipestates.SwipeState
import com.example.virusgame.game.zombie.PreAttackZombie
import com.example.virusgame.game.zombie.Zombie
import com.example.virusgame.game.zombie.ZombieDamageCalculator
import com.example.virusgame.game.zombie.ZombieMaker
import kotlinx.android.synthetic.main.main_menu.view.*

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback,
    EntityHandler {
    private val thread: GameThread
    private val gameStats = SaveManager.loadGameStats()
    private val eventManager = EventManager()
    private val ui = Ui(context)
    private val speech = Speech(context)
    private var zombie: Zombie? = null
    private var player = SaveManager.loadPlayer()
    private var sword = Sword(context)
    private val background = Background(context)

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
        SaveManager.loadEventManager(eventManager)
        eventManager.setupEvents(speech)
        spawnNewZombie()
        thread.setRunning(true)
        thread.start()
        FirstTimePlayingEvent.trigger()
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
        if(touched && zombie!!.state !is PreAttackZombie){
            swipeState = swipeState.onTouch(xTouch, yTouch, zombie!!)
            sword.update(xTouch.toFloat(), yTouch.toFloat())
        } else sword.deactivate()
        zombie!!.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        background.draw(canvas)
        zombie!!.draw(canvas)
        sword.draw(canvas)
        ui.drawHealth(canvas, player.health, player.maxHealth)
        ui.drawBorder(canvas)
        ui.drawWave(canvas, gameStats.wave)
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
        zombie = ZombieMaker().makeZombie(context, this, sword.offset, gameStats.wave)
    }

    override fun incrementZombieKillCount() {
        gameStats.incrementZombieKillCount()
        SaveManager.saveGame(player, gameStats, eventManager)
    }

    override fun inflictPlayerDamage(damage: Int) {
        player.takeDamage(damage)
    }
}

