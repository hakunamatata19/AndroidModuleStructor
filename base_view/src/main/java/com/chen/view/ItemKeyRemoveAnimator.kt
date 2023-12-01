package com.chen.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget1.RecyclerView
import com.chen.base_utils.KLog

public class ItemKeyRemoveAnimator : RecyclerView.ItemAnimator() {

    private val TAG = "ItemKeyRemoveAnimator"
    override fun animateDisappearance(
        viewHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo,
        postLayoutInfo: ItemHolderInfo?
    ): Boolean {
        KLog.d(TAG, "animateDisappearance pos:${viewHolder.layoutPosition}")

        val alphaAnimator =
            ObjectAnimator.ofFloat(viewHolder.itemView, View.ALPHA, 1.0f, 0.9f, 0.3f, 0.0f)
        alphaAnimator.setDuration(1000)
        alphaAnimator.repeatCount = 0


        val translateAnimation = ObjectAnimator.ofFloat(viewHolder.itemView,View.TRANSLATION_X,0f,-100f,-200f)
        val rotateAnimator = ObjectAnimator.ofFloat(viewHolder.itemView, View.ROTATION, 0f, -30f)
        rotateAnimator.setDuration(1000)
        val set = AnimatorSet()
        set.setDuration(1000)
        set.playTogether(alphaAnimator,translateAnimation)
        set.start()


       /* val alphaAnimation =  AlphaAnimation(1f,0.1f)

        val animationSet =   AnimationSet(false);
        animationSet.setDuration(3000)
        animationSet.addAnimation(  TranslateAnimation(0f, -100f, 0f, 0f));
        animationSet.addAnimation(  ScaleAnimation(0.1f, 1f, 0.1f, 1f));
        animationSet.setFillAfter(false)
        viewHolder.itemView.startAnimation(animationSet)*/


        return true
    }

    override fun animateAppearance(
        viewHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo?,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {

        KLog.d(TAG, "animateAppearance ${viewHolder.layoutPosition}");
        return false;
    }

    override fun animatePersistence(
        viewHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {
        KLog.d(TAG, "animatePersistence: ${viewHolder.layoutPosition}")






        return true
    }

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder,
        newHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {
        KLog.d(TAG, "animateChange: ${oldHolder.layoutPosition}");
        return true
    }


    override fun runPendingAnimations() {
        KLog.d(TAG, "runPendingAnimations");
    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {
        KLog.d(TAG, "endAnimation-- : ${item.layoutPosition}");
    }

    override fun endAnimations() {
        KLog.d(TAG, "endAnimations--");
    }

    override fun isRunning(): Boolean {
        KLog.d(TAG, "isRunning----");
        return false
    }


}