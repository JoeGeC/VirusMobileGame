package viruswiper.game.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import viruswiper.R
import viruswiper.ScreenDimensions

class WaveUi (context: Context, private val borderBottom: Float) {
    private val waveLabelPaint: Paint = Paint()
    private val wavePaint: Paint = Paint()
    private val waveString = context.getString(R.string.wave)

    init {
        waveLabelPaint.color = ContextCompat.getColor(context, R.color.white)
        waveLabelPaint.textAlign = Paint.Align.CENTER
        waveLabelPaint.textSize = ScreenDimensions.height / 50.0f

        wavePaint.color = ContextCompat.getColor(context, R.color.white)
        wavePaint.textAlign = Paint.Align.CENTER
        wavePaint.textSize = ScreenDimensions.width / 10.0f
    }

    fun draw(canvas: Canvas, wave: Int){
        canvas.drawText(waveString, ScreenDimensions.width / 2.0f, borderBottom - ScreenDimensions.height / 55.0f, waveLabelPaint)
        canvas.drawText(wave.toString(), ScreenDimensions.width / 2.0f, borderBottom + ScreenDimensions.height / 15.0f, wavePaint)
    }
}