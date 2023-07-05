package com.chen.base_data.repo

import com.chen.base_bean.BaseSong
import com.chen.base_bean.CollectionSong
import com.chen.base_data.UserAssetsDataBase

class LocalDBDataRepo(  manager: UserAssetsDataBase) :ALocalDataRepo(manager) {
    override fun updateData(): CollectionSong {
        TODO("Not yet implemented")
    }

    override fun addUserCollection(bean: BaseSong) {
        mLocalDataManager.runInTransaction(){
            //mLocalDataManager.collectionDao.insertSingle(bean)
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