package com.chen.agp.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chen.agp.R
import com.chen.base_bean.MusicDetail

class PagingItemViewHolder( container:ViewGroup)
    :ViewHolder(LayoutInflater.from(container.context).inflate(R.layout.cheese_item, container, false)) {
    var cheese: MusicDetail? = null
        private set
    private val nameView = itemView.findViewById<TextView>(R.id.name)


    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(item: MusicDetail?) {

            nameView.text = item?.title
            nameView.setTypeface(null, Typeface.NORMAL)

        cheese =  item
    }
}