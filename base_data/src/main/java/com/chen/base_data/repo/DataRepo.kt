package com.chen.base_data.repo

import androidx.lifecycle.LiveData

/**
 * 定义用户数据加载接口
 *  数据加载可以使用两个来源；
 *      1. 从网络加载
 *      2. 从本地数据库加载
 */
interface DataRepo<T,Coll,KeyType> {


    abstract  fun getDatas():LiveData<List<T>>

    abstract  fun updateData():T

    /**添加收藏数据*/
    abstract  fun addUserCollection(bean:Coll)

    /**删除收藏的数据*/
    abstract  fun deleteUserCollection(type:Int, key:KeyType)

    /**清除所有数据*/
    abstract fun clearData()
    /**用户账户变动*/
    abstract  fun onUserChanged()
}