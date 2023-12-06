package com.chen.keyrecycler

import androidx.recyclerview.widget1.RecyclerView

public class KeyEventLayoutManager:RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        TODO("Not yet implemented")
    }

    companion object  ItemConfig{

        val ITEM_POS_OFFSET= 40 //item的位置偏移量

        val ITEM_SCALE_OFFSET= 0.08f //item的缩小和放大偏移量


    }

    override fun scrollToPosition(position: Int) {
        super.scrollToPosition(position)
    }
}