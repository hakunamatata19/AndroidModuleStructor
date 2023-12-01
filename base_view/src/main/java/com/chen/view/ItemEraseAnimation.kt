package com.chen.view

import android.view.animation.Animation

class ItemEraseAnimation(var mFromDegree:Float,
                         var mToDegree:Float,
                         var mFromX:Float,
                         var mFromY:Float,
                         var depth:Float,
                         var reverse:Boolean=false
) :Animation() {

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
    }





}