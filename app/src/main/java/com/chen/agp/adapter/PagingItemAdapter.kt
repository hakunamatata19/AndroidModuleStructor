package com.chen.agp.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.chen.base_bean.MusicDetail
import com.chen.base_utils.KLog

class PagingItemAdapter: PagingDataAdapter<MusicDetail,PagingItemViewHolder>(diffCallback) {
    private   val TAG = "PagingItemAdapter"
    override fun onBindViewHolder(holder: PagingItemViewHolder, position: Int) {
        holder.bindTo(getItem(position) )
    }

    override fun onBindViewHolder(
        holder: PagingItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }



    override fun getItemCount(): Int {
        val count= super.getItemCount()
        return count
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingItemViewHolder {
       return  PagingItemViewHolder(parent)
    }


    companion object {
        private const val TAG = "PagingItemAdapter"
        val diffCallback = object : DiffUtil.ItemCallback<MusicDetail>() {
            //判断两个Item是否是相同的，一般通过唯一Id，
            //
            override fun areItemsTheSame(oldItem: MusicDetail, newItem: MusicDetail): Boolean {
                KLog.d(TAG,"areItemsTheSame：${oldItem.title} ")
                KLog.d(TAG,"areItemsTheSame：${newItem.title} ")
                return if (oldItem is MusicDetail  && newItem is MusicDetail) {
                    oldItem.id == newItem.id
                }   else {
                    false
                }
            }

            //判断内容是否发生了变更，
            //例如  一个Item 被收藏或者取消收藏，这个时候就需要进行界面的变更。
            override fun areContentsTheSame(oldItem: MusicDetail, newItem: MusicDetail): Boolean {
                KLog.d(TAG,"aCarentsSame: ${oldItem?.id}");
                KLog.d(TAG,"aCarentsSame: ${newItem?.id}");
                return oldItem.equals(newItem)
            }

        }
    }
}