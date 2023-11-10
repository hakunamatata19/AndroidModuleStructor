package com.chen.agp

import android.os.Bundle
import android.os.PersistableBundle
import com.chen.agp.databinding.ActivityPaingLayoutBinding

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chen.agp.adapter.PagingItemAdapter
import com.chen.base_utils.KLog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingDataActivity :AppCompatActivity() {

    private   val TAG = "PagingDataActivity"
    lateinit var binding: ActivityPaingLayoutBinding
        private set
    private val viewModel by viewModels<PagingViewModel> { AGPViewModelFactory(application) }
    private lateinit var   mAdapter:PagingItemAdapter
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaingLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
      mAdapter = PagingItemAdapter()
        binding.pagingContainer.adapter =mAdapter
        binding.pagingContainer.layoutManager= LinearLayoutManager(this@PagingDataActivity,
            LinearLayoutManager.VERTICAL,false)
       lifecycleScope.launch {
                viewModel.allMusicList.collectLatest   {
                    KLog.d(TAG,"onItemReceived ${it}");
                    mAdapter.submitData(it)
                }

       }
        mAdapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                KLog.d(TAG,"onChanged---");
                super.onChanged()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                KLog.d(TAG,"onItemRangeChanged---");
                super.onItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                KLog.d(TAG,"onItemRangeInserted...");
                super.onItemRangeInserted(positionStart, itemCount)
            }
        });
        mAdapter.addLoadStateListener {
                state ->
            when(state.refresh){
                is LoadState.Loading -> {
                   KLog.d(TAG,"onLoading ${mAdapter.itemCount}");
                }
                is LoadState.NotLoading -> {
                    KLog.d(TAG,"notLoading ${mAdapter.itemCount}");
                }
                is LoadState.Error -> {
                    // show an error dialog.
                    KLog.d(TAG,"loadError");
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()

    }


}