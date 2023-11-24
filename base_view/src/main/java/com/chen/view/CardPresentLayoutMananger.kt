package com.chen.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget1.RecyclerView
import androidx.recyclerview.widget1.RecyclerView.Recycler
import com.chen.base_utils.KLog


/**
 * 支持View进行层级对方的LayoutManager
 */
class CardPresentLayoutMananger :RecyclerView.LayoutManager() {
    private   val TAG = "CardPresentLayoutManang"

    private val LAYOUT_ITEM_COUNT =4; // 当前View可以摆放的最多的Item个数。

    private val ANGEL_DIFF = 15; //每次偏移15度。

    private val SIZE_DIFF = 0.08// 每次缩小固定的大小。1， 092. 0.84，0.76


    override fun onMeasure(
        recycler: Recycler,
        state: RecyclerView.State,
        widthSpec: Int,
        heightSpec: Int
    ) {
        super.onMeasure(recycler, state, widthSpec, heightSpec)
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun isAutoMeasureEnabled(): Boolean {
        return true
    }
    override fun onLayoutChildren(recycler: Recycler?, state: RecyclerView.State?) {
        KLog.d(TAG,"startLayoutChildren $recycler,state:$state");

        if (recycler != null) {
            KLog.d(TAG,"startDetachedScrap--");
            //首先进行一次回收，根据不同的情况回收当前的RecyclerViewItem。
            detachAndScrapAttachedViews(recycler)
            KLog.d(TAG,"endScrap--");
        }

        //然后开始排版放置当前的ItemView
        val currentNum =Math.min(LAYOUT_ITEM_COUNT,itemCount)-1

        for (x in 0..currentNum){
            val pos =x;
            KLog.d(TAG,"StartLaoutItemPos:$pos")
             layoutSingleItem(nextView(pos,recycler),pos,)
        }

    }

    /**
     * 针对固定位置的View进行摆放
     * 主要是需要控制  旋转角度，同时进行大小缩放。
     */
    private fun layoutSingleItem(view:View?,pos:Int){
        if(view ==null ){
            KLog.d(TAG,"can't  get view for position：$pos,view:$view")
        }
        KLog.d(TAG,"addItemView:pos:$pos,view:$view")
        KLog.d(TAG,"addItemView:layoutPa:${view?.layoutParams}")
        KLog.d(TAG,"addItemView:layoutPa:${view?.visibility}")
        val params :RecyclerView.LayoutParams = view!!.layoutParams as RecyclerView.LayoutParams
        // view.rotation= (ANGEL_DIFF *pos).toFloat()
        addView(view)
        measureChildWithMargins(view,0,0)



        layoutDecoratedWithMargins(view,params.leftMargin,params.topMargin,0,0)
    }



    private fun nextView(posDiff:Int,recycler:Recycler?):View?{
        val itemView= recycler?.getViewForPosition(posDiff)
        KLog.d(TAG,"getItemView:$itemView,pos:$posDiff");
        return itemView
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun getItemViewType(view: View): Int {
        return super.getItemViewType(view)
    }

    override fun canScrollHorizontally(): Boolean {
        return super.canScrollHorizontally()
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        KLog.d(TAG,"layoutComplete: ${itemCount}");
    }
}