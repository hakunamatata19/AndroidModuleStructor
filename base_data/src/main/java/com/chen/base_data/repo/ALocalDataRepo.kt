package com.chen.base_data.repo

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.chen.base_bean.BaseSong
import com.chen.base_bean.CollectionSong
import com.chen.base_data.UserAssetsDataBase

/**
 * 本地数据存储，可能是一个数据库，也可能是一个MMKv
 *
 */
abstract  class ALocalDataRepo(protected  val mLocalDataManager:UserAssetsDataBase)
    :DataRepo<CollectionSong,BaseSong,String> {

    private lateinit var mSongCollectAll:MediatorLiveData<List<CollectionSong>>
 init {
     mSongCollectAll = MediatorLiveData()

     mSongCollectAll.addSource(mLocalDataManager.collectionDao.getAllSongDetail(),
     object:Observer<List<CollectionSong>>{
         override fun onChanged(value: List<CollectionSong>) {
              if(mLocalDataManager.databaseCreated.value != null){
                  mSongCollectAll.postValue(value)
              }
         }

     })
 }
    override fun getDatas(): LiveData<List<CollectionSong>> {
        return mSongCollectAll
    }



}