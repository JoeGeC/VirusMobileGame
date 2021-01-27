package viruswiper.game

import android.graphics.Rect

interface SwipeTaker {
    fun onSuccessfulSwipe()
    var hitRect: Rect
}
