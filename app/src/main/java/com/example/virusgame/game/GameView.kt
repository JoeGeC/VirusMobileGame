package com.example.virusgame.game

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.example.virusgame.R
import com.example.virusgame.game.swipestates.StartSwipeState
import com.example.virusgame.game.swipestates.SwipeState

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread
    private var zombie: Zombie? = null

    private var touched: Boolean = false
    private var xTouch: Int = 0
    private var yTouch: Int = 0
    private var swipeState: SwipeState = StartSwipeState()

    private val zombieHealthPaint: Paint = Paint()

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
        zombieHealthPaint.color = ContextCompat.getColor(context, R.color.white)
        zombieHealthPaint.textSize = 50.0f
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
        if(touched) swipeState = swipeState.onTouch(xTouch, yTouch, zombie!!)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        zombie!!.draw(canvas)

        val zombieHealthText = "Health: " + zombie!!.health
        canvas.drawText(zombieHealthText, 0, zombieHealthText.length, (Resources.getSystem().displayMetrics.widthPixels - 300).toFloat(), 150.0f, zombieHealthPaint)
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
}

