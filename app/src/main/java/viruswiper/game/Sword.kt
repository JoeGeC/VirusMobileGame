package viruswiper.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import viruswiper.R
import viruswiper.game.vector2.FloatVector2
import viruswiper.game.vector2.IntVector2

class Sword(context: Context) {
    private var position = FloatVector2(0f, 0f)
    private var image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.short_sword)
    private var show: Boolean = false
    var active: Boolean = true

    fun draw(canvas: Canvas) {
        if(show && active)
            canvas.drawBitmap(image, position.x - image.width, position.y, null)
    }

    fun update(touchPos: IntVector2){
        position = touchPos.toFloat()
        show = true
    }

    fun deactivate() {
        show = false
    }
}