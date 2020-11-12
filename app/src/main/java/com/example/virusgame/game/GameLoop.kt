package com.example.virusgame.game

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.game.doubleSwipe.DoubleSwipeHandler
import com.example.virusgame.game.events.EventManager
import com.example.virusgame.game.events.FirstTimePlayingEvent
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.rotation.RotationHandler
import com.example.virusgame.game.rotation.RotationReceiver
import com.example.virusgame.game.swipestates.StartSwipeState
import com.example.virusgame.game.swipestates.SwipeState
import com.example.virusgame.game.ui.Ui
import com.example.virusgame.game.uiHandlers.ShopHandler
import com.example.virusgame.game.uiHandlers.UiHandler
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.*
import com.example.virusgame.shop.items.ShopItem

class GameLoop(override var context: Context) : EntityHandler, UiHandler, DoubleSwipeHandler,
    RotationHandler {
    private var location: Double = 0.0
    private val gameStats = SaveManager.loadGameStats()
    private val eventManager = EventManager()
    private val ui = Ui(context)
    private val speech = Speech(context)
    private var zombie: Zombie? = null
    private var player = Player()
    private var sword = Sword(context)
    private val background = Background(context)
    lateinit var shopHandler: ShopHandler

    private var touched: Boolean = false
    private var touchPos: IntVector2 = IntVector2(0, 0)
    private var startTouchPos: IntVector2 = IntVector2(0, 0)
    private var swipeState: SwipeState = StartSwipeState()
    private var rotationReceiver: RotationReceiver = RotationReceiver(context, this)
    private var shakeReceiver: ShakeReceiver = ShakeReceiver(context, this)

    init {
        player = SaveManager.loadPlayer()
        player.setupPlayer(this)
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
        zombie!!.update(location)
    }

    fun draw(canvas: Canvas) {
        background.draw(canvas)
        zombie!!.draw(canvas)
        ui.draw(canvas, player, gameStats)
        speech.draw(canvas)
        sword.draw(canvas)
        player.ability?.draw(canvas)
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

    override fun inflictZombieDamage(damage: Int) {
        zombie!!.takeDamage(damage)
    }

    override fun getPlayerAttack(): Int {
        return player.attack
    }

    override fun shakeZombie() {
        zombie!!.onShake(player.attack)
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

    override fun abilityUsed() {
    }

    override fun openShop() {
        if(zombie!!.state !is PreAttackZombie && zombie!!.state !is AttackZombie){
            zombie!!.active = false
            zombie!!.deactivatedTime = System.nanoTime()
            sword.active = false
            shopHandler.openShop()
        }
    }

    override fun closeShop(){
        zombie!!.active = true
        sword.active = true
        zombie!!.adjustLastAttackTimeForDeactivation()
        SaveManager.saveGame(player, gameStats, eventManager)
    }

    override fun purchase(shopItem: ShopItem): Boolean {
        if(canPurchase(shopItem)){
            player.gold -= shopItem.price
            return true
        }
        return false
    }

    override fun canPurchase(shopItem: ShopItem): Boolean {
        return player.gold >= shopItem.price
    }

    override fun use(shopItem: ShopItem) {
        shopItem.use(player, this)
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

    override fun onSuccessfulDoubleSwipe() {
        player.useAbility()
    }

    override fun onRotate(pitch: Double, tilt: Double, azimuth: Double) {
        location = azimuth
    }
}