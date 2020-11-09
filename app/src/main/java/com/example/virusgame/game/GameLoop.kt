package com.example.virusgame.game

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.game.events.EventManager
import com.example.virusgame.game.events.FirstTimePlayingEvent
import com.example.virusgame.game.swipestates.StartSwipeState
import com.example.virusgame.game.swipestates.SwipeState
import com.example.virusgame.game.ui.DeathHandler
import com.example.virusgame.game.ui.Ui
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.PreAttackZombie
import com.example.virusgame.game.zombie.Zombie
import com.example.virusgame.game.zombie.ZombieDamageCalculator
import com.example.virusgame.game.zombie.ZombieMaker

class GameLoop(private var context: Context) : EntityHandler, DeathHandler {
    private val gameStats = SaveManager.loadGameStats()
    private val eventManager = EventManager()
    private val ui = Ui(context)
    private val speech = Speech(context)
    private var zombie: Zombie? = null
    private var player = Player(this)
    private var sword = Sword(context)
    private val background = Background(context)

    private var touched: Boolean = false
    private var touchPos: IntVector2 = IntVector2(0, 0)
    private var startTouchPos: IntVector2 = IntVector2(0, 0)
    private var swipeState: SwipeState = StartSwipeState()

    init {
        player = SaveManager.loadPlayer(this)
        ZombieDamageCalculator.player = player
        SaveManager.loadEventManager(eventManager)
        eventManager.setupEvents(speech)
        spawnNewZombie()
        FirstTimePlayingEvent.trigger()
    }

    fun update(){
        if(touched && zombie!!.state !is PreAttackZombie){
            swipeState = swipeState.onTouch(touchPos, zombie!!)
            sword.update(touchPos)
        } else sword.deactivate()
        zombie!!.update()
    }

    fun draw(canvas: Canvas) {
        background.draw(canvas)
        zombie!!.draw(canvas)
        sword.draw(canvas)
        ui.draw(canvas, player, gameStats)
        speech.draw(canvas)
    }

    fun updateTouchPos(event: MotionEvent) {
        touchPos.x = event.x.toInt()
        touchPos.y = event.y.toInt()
    }

    fun updateTouchStartPos() {
        startTouchPos = touchPos
        touched = true
    }

    fun releaseTouch() {
        touched = false
        swipeState = StartSwipeState()
        speech.onTouch(startTouchPos)
        ui.onTouch(startTouchPos, touchPos, this)
    }

    override fun takeGold(gold: Int) {
        player.increaseGold(gold)
    }

    override fun spawnNewZombie() {
        zombie = when (gameStats.zombieWaveKillCount) {
            gameStats.waveAmount - 1 -> ZombieMaker().makeBossZombie(context, this, sword.offset, gameStats.wave, player.maxHealth + player.attack)
            else -> ZombieMaker().makeZombie(context, this, sword.offset, gameStats.wave, player.maxHealth + player.attack)
        }
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
        ui.death.active = true
    }

    override fun upgradeAttack() {
        if(!player.upgradeAttack())
            speech.setSpeechText(context.getString(R.string.not_enough_gold))
    }

    override fun upgradeHealth() {
        if(!player.upgradeHealth())
            speech.setSpeechText(context.getString(R.string.not_enough_gold))
    }

    override fun revive() {
        gameStats.wave = 1
        gameStats.zombieWaveKillCount = 0
        player.restoreHealthToMax()
        spawnNewZombie()
        sword.active = true
        zombie!!.active = true
        ui.death.active = false
    }
}