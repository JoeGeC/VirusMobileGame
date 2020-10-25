package com.example.virusgame

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

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread
    private var zombie: Zombie? = null

    private var touched: Boolean = false
    private var xTouch: Int = 0
    private var yTouch: Int = 0
    private var swipeState: SwipeState = SwipeState.START

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
        if(touched) handleZombieDamage()
    }

    private fun handleZombieDamage() {
        if(swipeState == SwipeState.START){
            if(xTouch > zombie!!.rect.right || xTouch < zombie!!.rect.left || yTouch < zombie!!.rect.top || yTouch > zombie!!.rect.bottom){
                swipeState = SwipeState.OUT
                return
            }
        }else if(swipeState == SwipeState.OUT){
            if(xTouch < zombie!!.rect.right && xTouch > zombie!!.rect.left && yTouch > zombie!!.rect.top && yTouch < zombie!!.rect.bottom){
                swipeState = SwipeState.ENTER
                return
            }
        }else if(swipeState == SwipeState.ENTER){
            if(xTouch > zombie!!.rect.right || xTouch < zombie!!.rect.left || yTouch < zombie!!.rect.top || yTouch > zombie!!.rect.bottom){
                swipeState = SwipeState.EXIT
                zombie!!.takeDamage(1)
                swipeState = SwipeState.START
                return
            }
        }
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
        swipeState = SwipeState.START
    }
}

enum class SwipeState {
    START,
    OUT,
    ENTER,
    EXIT
}
