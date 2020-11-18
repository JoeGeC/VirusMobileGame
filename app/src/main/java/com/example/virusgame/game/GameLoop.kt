package com.example.virusgame.game

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import com.example.virusgame.R
import com.example.virusgame.SaveManager
import com.example.virusgame.SpeechSetter
import com.example.virusgame.WaveListener
import com.example.virusgame.death.DeathHandler
import com.example.virusgame.death.DeathListener
import com.example.virusgame.death.DeathUiHandler
import com.example.virusgame.game.collector.Collector
import com.example.virusgame.game.collector.CollectorManager
import com.example.virusgame.game.collector.GoldCollector
import com.example.virusgame.game.collector.ZombieHeartCollector
import com.example.virusgame.game.doubleSwipe.DoubleSwipeHandler
import com.example.virusgame.game.events.EventManager
import com.example.virusgame.game.events.IntroEvent
import com.example.virusgame.game.player.Player
import com.example.virusgame.game.rotation.RotationHandler
import com.example.virusgame.game.rotation.RotationReceiver
import com.example.virusgame.game.swipestates.StartSwipeState
import com.example.virusgame.game.swipestates.SwipeState
import com.example.virusgame.game.ui.Ui
import com.example.virusgame.game.vector2.FloatVector2
import com.example.virusgame.game.vector2.IntVector2
import com.example.virusgame.game.zombie.ZombieDamageCalculator
import com.example.virusgame.game.zombie.ZombieMaker
import com.example.virusgame.game.zombie.states.PreAttackZombie
import com.example.virusgame.game.zombie.types.Zombie
import com.example.virusgame.shop.ShopHandler
import com.example.virusgame.shop.ShopList
import com.example.virusgame.shop.items.ShopItem
import kotlin.random.Random

class GameLoop(override var context: Context) : EntityHandler, DeathHandler, ShopHandler, DoubleSwipeHandler,
    RotationHandler, CollectorManager {
    private var location: Double = 0.0
    private val gameStats = SaveManager.loadGameStats()
    private val eventManager = EventManager()
    private val ui = Ui(context)
    private lateinit var speech: SpeechSetter
    private var zombie: Zombie? = null
    override var player = Player()
    private var sword = Sword(context)
    private val background = Background(context)
    private val collectors = mutableListOf<Collector>()
    private val chest = Chest(this)
    private var deathUiHandler: DeathUiHandler? = null
    private lateinit var deathListener: DeathListener

    private var touched: Boolean = false
    private var touchPos: IntVector2 = IntVector2(0, 0)
    private var startTouchPos: IntVector2 = IntVector2(0, 0)
    private var swipeState: SwipeState = StartSwipeState()
    private var rotationReceiver: RotationReceiver = RotationReceiver(context, this)
    private var shakeReceiver: ShakeReceiver = ShakeReceiver(context, this)

    init {
        player = SaveManager.loadPlayer()
        player.setup(this)
        ZombieDamageCalculator.player = player
    }

    fun lateInit(speechSetter: SpeechSetter, waveListener: WaveListener, death: DeathListener){
        speech = speechSetter
        setupEvents()
        gameStats.assignWaveListener(waveListener)
        spawnNewZombie()
        deathListener = death
    }

    private fun setupEvents() {
        SaveManager.loadEventManager(eventManager)
        eventManager.setupEvents(speech)
        IntroEvent.trigger()
    }

    fun update(){
        if(touched && zombie!!.state !is PreAttackZombie){
            swipeState = swipeState.onTouch(touchPos, zombie!!)
            sword.update(touchPos)
        } else sword.deactivate()
        zombie!!.update(location)
        chest.update(location)
    }

    fun draw(canvas: Canvas) {
        background.draw(canvas)
        chest.draw(canvas)
        zombie!!.draw(canvas)
        ui.draw(canvas, player, gameStats)
        sword.draw(canvas)
        player.ability?.draw(canvas)
        for (collector in collectors) collector.draw(canvas)
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
        ui.onTouch(startTouchPos, touchPos)
        chest.onTouch(startTouchPos, touchPos)
    }

    override fun onZombieDeath(gold: Int, zombieHearts: Int, zombiePosition: FloatVector2) {
        if(zombieHearts > 0) addCollector(ZombieHeartCollector(zombiePosition.offsetX(100), zombieHearts, this))
        addCollector(GoldCollector(zombiePosition.offsetX(-100), gold, this))
        IntroEvent.completeEvent()
        incrementZombieKillCount()
        spawnNewZombie()
        if(Random.nextInt(12) == 0 || gameStats.zombieWaveKillCount == 4) chest.spawn(gameStats.getCurrentWave())
    }

    private fun spawnNewZombie() {
        zombie = when (gameStats.zombieWaveKillCount) {
            gameStats.waveAmount - 1 -> ZombieMaker().makeBossZombie(context, this, sword.offset, gameStats.getCurrentWave(), player.maxHealth + player.attack)
            else -> ZombieMaker().makeZombie(context, this, sword.offset, gameStats.getCurrentWave(), player.maxHealth + player.attack)
        }
    }

    private fun incrementZombieKillCount() {
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
        speech.setMessage(context.getString(R.string.death_message))
        sword.active = false
        zombie!!.active = false
        deathListener.onPlayerDeath()
        chest.reset()
    }

    override fun onMenuOpened() { pause() }

    override fun onMenuClosed(){
        resume()
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
            speech.setMessage(context.getString(R.string.not_enough_boss_hearts))
        deathUiHandler?.updateAttackValues(player.attack, player.attackBuyValue)
    }

    override fun upgradeHealth() {
        if(!player.upgradeHealth())
            speech.setMessage(context.getString(R.string.not_enough_boss_hearts))
        deathUiHandler?.updateHealthValues(player.maxHealth, player.maxHealthBuyValue)
    }

    override fun revive() {
        gameStats.resetWave()
        gameStats.zombieWaveKillCount = 0
        player.revive()
        SaveManager.saveShop(ShopList.newShop(context).map { it.saveData })
        spawnNewZombie()
        sword.active = true
    }

    override fun setDeathUiHandler(handler: DeathUiHandler) {
        deathUiHandler = handler
        deathUiHandler?.updateAttackValues(player.attack, player.attackBuyValue)
        deathUiHandler?.updateHealthValues(player.maxHealth, player.maxHealthBuyValue)
    }

    override fun onSuccessfulDoubleSwipe() {
        player.useAbility()
    }

    override fun onRotate(pitch: Double, tilt: Double, azimuth: Double) {
        location = azimuth
    }

    fun pause() {
        zombie!!.deactivate()
        sword.active = false
        shakeReceiver.onPause()
        rotationReceiver.onPause()
    }

    fun resume(){
        zombie!!.resume()
        sword.active = true
        shakeReceiver.onResume()
        rotationReceiver.onResume()
    }

    override fun destroyCollector(collector: Collector) {
        collectors.remove(collector)
    }

    override fun addCollector(collector: Collector) {
        collectors.add(collector)
    }
}