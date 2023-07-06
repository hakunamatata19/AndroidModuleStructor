package com.chen.base_data.repo

import com.chen.base_bean.BaseSong
import com.chen.base_bean.CollectionSong
import com.chen.base_data.UserAssetsDataBase

class LocalDBDataRepo private  constructor(manager: UserAssetsDataBase) : ALocalDataRepo(manager) {



    override fun updateData(): CollectionSong {
        TODO("Not yet implemented")
    }

   companion object{
       var sInstance:LocalDBDataRepo? =null
       @Synchronized
       fun getInstance(manager:UserAssetsDataBase):LocalDBDataRepo{
           if(sInstance ==null){
               sInstance = LocalDBDataRepo(manager)
           }
           return  sInstance!!
       }
   }

    override fun addUserCollection(bean: BaseSong) {
        mLocalDataManager.runInTransaction() {
            val song = CollectionSong()
            song.userId = "22"
            song.songId = bean.songId
            song.songTitle = bean.songTitle
            mLocalDataManager.collectionDao.insertSingle(song)
        }
    }

    override fun deleteUserCollection(type: Int, key: String) {
        TODO("Not yet implemented")
    }

    override fun clearData() {
        TODO("Not yet implemented")
    }

    override fun onUserChanged() {
        TODO("Not yet implemented")
    }
}