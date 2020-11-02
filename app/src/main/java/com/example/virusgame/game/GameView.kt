package com.example.virusgame.game

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.game.events.EventManager
import com.example.virusgame.game.events.FirstTimePlayingEvent
import com.example.virusgame.game.swipestates.StartSwipeState
import com.example.virusgame.game.swipestates.SwipeState
import com.example.virusgame.game.ui.Ui
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.PreAttackZombie
import com.example.virusgame.game.zombie.Zombie
import com.example.virusgame.game.zombie.ZombieDamageCalculator
import com.example.virusgame.game.zombie.ZombieMaker

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback,
    EntityHandler {
    private val thread: GameThread
    private val gameStats = SaveManager.loadGameStats()
    private val eventManager = EventManager()
    private val ui = Ui(context)
    private val speech = Speech(context)
    private var zombie: Zombie? = null
    private var player = SaveManager.loadPlayer(this)
    private var sword = Sword(context)
    private val background = Background(context)

    private var touched: Boolean = false
    private var touchPos: IntVector2 = IntVector2(0, 0)
    private var startTouchPos: IntVector2 = IntVector2(0, 0)
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
            swipeState = swipeState.onTouch(touchPos, zombie!!)
            sword.update(touchPos)
        } else sword.deactivate()
        zombie!!.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        background.draw(canvas)
        zombie!!.draw(canvas)
        sword.draw(canvas)
        ui.draw(canvas, player, gameStats)
        speech.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        touchPos.x = event.x.toInt()
        touchPos.y = event.y.toInt()

        when(event.action){
            MotionEvent.ACTION_DOWN -> updateTouchStartPos()
            MotionEvent.ACTION_MOVE -> touched = true
            MotionEvent.ACTION_UP -> releaseTouch()
        }
        return true
    }

    private fun updateTouchStartPos() {
        startTouchPos = touchPos
        touched = true
    }

    private fun releaseTouch() {
        touched = false
        swipeState = StartSwipeState()
        speech.onTouch(startTouchPos)
        ui.onTouch(startTouchPos, touchPos)
    }

    override fun takeGold(gold: Int) {
        player.increaseGold(gold)
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

    override fun onPlayerDeath() {
        speech.setSpeechText(context.getString(R.string.death_message))
        sword.active = false
        zombie!!.active = false
        showDeathUi()
    }

    private fun showDeathUi() {
        ui.death.active = true

    }
}

