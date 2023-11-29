package com.chen.view

import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget1.RecyclerView
import androidx.recyclerview.widget1.RecyclerView.Recycler
import com.chen.base_utils.KLog

class MySimpleLayoutManager(var mClickListener:OnClickListener) : RecyclerView.LayoutManager(), View.OnClickListener {

    public val ITEM_COUNT = 4
    public val POSITION_OFFSET = 40
    public val SCALE_OFFSET = 0.08

    private val TAG = "MySimpleLayoutManager"

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        detachAndScrapAttachedViews(recycler)

        var tempCount = getItemCount()
        tempCount = Math.min(tempCount, ITEM_COUNT)

        KLog.d(TAG, "LayoutItemCount : $tempCount")


        for (x in tempCount - 1 downTo 0) {
            KLog.d(TAG, "startLayoutItem:$x")
           val view= recycler.getViewForPosition(x)
            addView(view)
          //val params =  view.layoutParams as RecyclerView.LayoutParams;

            view.scaleX = (1- x * SCALE_OFFSET).toFloat()
            view.scaleY = (1- x * SCALE_OFFSET ).toFloat()
            view.rotation = 10*x.toFloat()
            view.translationY = (x*POSITION_OFFSET).toFloat()
            measureChildWithMargins(view,0,0)
            val widthSpace = width - getDecoratedMeasuredWidth(view)
            val heightSpace = height - getDecoratedMeasuredHeight(view)
            layoutDecoratedWithMargins(
                view, widthSpace / 2, heightSpace / 5,
                widthSpace / 2 + getDecoratedMeasuredWidth(view),
                heightSpace / 5 + getDecoratedMeasuredHeight(view)
            )

        }


    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        for (x in 0 until  childCount){
            getChildAt(x)!!.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        val pos = getPosition(v)
        mClickListener?.onClick(v)
    }
}