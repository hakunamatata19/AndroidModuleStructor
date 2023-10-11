package com.chen.agp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chen.agp.databinding.CheeseItemBinding

/**
 * 带有加载状态的Adapter。
 */
class LoadingAdapter(val retry:()->Unit):LoadStateAdapter<LoadingAdapter.BindingViewHolder>() {
    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {
        when(loadState){
            is LoadState.Loading -> {
               /* holder.binding.progressBar.visibility = View.VISIBLE
                holder.binding.errorMsg.visibility = View.INVISIBLE
                holder.binding.retryButton.visibility = View.INVISIBLE*/
            }
            is LoadState.NotLoading -> {
               /* holder.binding.progressBar.visibility = View.INVISIBLE
                holder.binding.errorMsg.visibility = View.INVISIBLE
                holder.binding.retryButton.visibility = View.INVISIBLE*/
            }
            is LoadState.Error -> {
               /* holder.binding.progressBar.visibility = View.INVISIBLE
                holder.binding.errorMsg.visibility = View.VISIBLE
                holder.binding.retryButton.visibility = View.VISIBLE*/
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BindingViewHolder {
        return BindingViewHolder(
            CheeseItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }


    class BindingViewHolder(val binding:CheeseItemBinding):ViewHolder(binding.root)
}