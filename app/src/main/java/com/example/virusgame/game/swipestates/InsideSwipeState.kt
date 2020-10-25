package com.example.virusgame.game.swipestates

import com.example.virusgame.game.SwipeTaker

class InsideSwipeState(private val enterXTouch: Int, private val enterYTouch: Int) : SwipeState {
      override fun onTouch(xTouch: Int, yTouch: Int, swipeTaker: SwipeTaker): SwipeState {
          if(xTouch > swipeTaker.rect.right || xTouch < swipeTaker.rect.left || yTouch < swipeTaker.rect.top || yTouch > swipeTaker.rect.bottom){
              return StartSwipeState()
          }
          return this
      }

//    override fun onTouch(xTouch: Int, yTouch: Int, swipeTaker: SwipeTaker): SwipeState {
////        if(enterXTouch <= swipeTaker.rect.left + swipeTaker.rect.width() / 2){
////            if(xTouch > swipeTaker.rect.right || yTouch < swipeTaker.rect.top || yTouch > swipeTaker.rect.bottom){
////                swipeTaker.onSuccessfulSwipe()
////                return StartSwipeState()
////            } else if (xTouch < swipeTaker.rect.left){
////                return StartSwipeState()
////            }
////        }
////        if(enterXTouch >= swipeTaker.rect.right - swipeTaker.rect.width() / 2){
////            if(xTouch < swipeTaker.rect.left || yTouch < swipeTaker.rect.top || yTouch > swipeTaker.rect.bottom){
////                swipeTaker.onSuccessfulSwipe()
////                return StartSwipeState()
////            } else if (xTouch > swipeTaker.rect.right){
////                return StartSwipeState()
////            }
////        }
////        if(enterYTouch <= swipeTaker.rect.top + swipeTaker.rect.height() / 2){
////            if(xTouch > swipeTaker.rect.right || xTouch < swipeTaker.rect.left || yTouch > swipeTaker.rect.bottom){
////                swipeTaker.onSuccessfulSwipe()
////                return StartSwipeState()
////            } else if (yTouch < swipeTaker.rect.top){
////                return StartSwipeState()
////            }
////        }
////        if(enterYTouch >= swipeTaker.rect.bottom - swipeTaker.rect.height() / 2){
////            if(xTouch > swipeTaker.rect.right || xTouch < swipeTaker.rect.left || yTouch < swipeTaker.rect.top){
////                swipeTaker.onSuccessfulSwipe()
////                return StartSwipeState()
////            } else if (yTouch > swipeTaker.rect.bottom){
////                return StartSwipeState()
////            }
////        }
//
//
////        if(xTouch > swipeTaker.rect.right || xTouch < swipeTaker.rect.left || yTouch < swipeTaker.rect.top || yTouch > swipeTaker.rect.bottom){
////            swipeTaker.onSuccessfulSwipe()
////            return StartSwipeState()
////        }
//        return this
//    }
}
