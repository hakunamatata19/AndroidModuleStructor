package com.chen.agp

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.chen.base_bean.CollectionSong
import com.chen.base_data.UserAssetsDataBase
import com.chen.base_utils.KLog
import com.chen.base_view.viewmodel.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MySecondActivity:BaseActivity<SecondViewModel>(), View.OnClickListener {
    private  val TAG = "MySecondActivity"
  //   lateinit var   viewModel:SecondViewModel

    private lateinit var  mBtnAdd:AppCompatButton
    private lateinit var mBtnClear:AppCompatButton
    private lateinit var  mBtnCheck:AppCompatButton


    override fun getViewMode(): SecondViewModel {
        return  ViewModelProvider(this@MySecondActivity).get(SecondViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initView() {
        setContentView(R.layout.second_activity)
        mBtnAdd=findViewById(R.id.second_btn_add)
        mBtnClear=findViewById(R.id.second_btn_delete)
        mBtnCheck=findViewById(R.id.second_btn_request)

        mBtnAdd.setOnClickListener(this@MySecondActivity)
        mBtnClear.setOnClickListener(this@MySecondActivity)
        mBtnCheck.setOnClickListener(this@MySecondActivity)
    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        mViewModel?.rollDice()
    }

    override fun onPause() {
        super.onPause()
        mViewModel?.rollDice()
    }

    override fun onClick(v: View?) {
         when(v?.id){
             R.id.second_btn_add->{
                mViewModel?.viewModelScope?.launch (Dispatchers.IO){
                    for (x in 0..50){
                        val song = CollectionSong()
                        song.songName="song$x"
                        song.songTitle="songTitle$x"
                        song.songId ="songId$x"
                        song.singerName="singer$x"
                        song.albumName="albume$x"
                        song.addTime=System.currentTimeMillis()
                        song.addType = "collection"
                        song.userId="UserId_chen"
                        UserAssetsDataBase.getInstance(this@MySecondActivity,(this@MySecondActivity.application as BasicApp).getmAppExecutors())
                            .collectionDao.insertAll(song)
                    }
                }
             }

             R.id.second_btn_delete->{
                 lifecycleScope.launch (Dispatchers.IO){
                     UserAssetsDataBase.getInstance(this@MySecondActivity,(this@MySecondActivity.application as BasicApp).getmAppExecutors())
                         .collectionDao.clearAllSong("collection")
                 }
             }
             R.id.second_btn_request->{
                 lifecycleScope.launch {
                     UserAssetsDataBase.getInstance(this@MySecondActivity,(this@MySecondActivity.application as BasicApp).getmAppExecutors())
                         .collectionDao.getAllInMain().collect(){
                            KLog.d(TAG,"onSongCollected:${it.size}")
                         }
                 }
             }
         }
    }
}