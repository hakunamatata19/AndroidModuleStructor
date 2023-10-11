package com.chen.agp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.loader.content.Loader
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.chen.base_bean.MusicDetail
import com.chen.base_http.business.MusicMediaMethod
import com.chen.base_utils.KLog
import java.lang.Exception
import java.lang.IllegalArgumentException

class PagingViewModel :ViewModel(){
    private   val TAG = "PagingViewModel"
    private var  mMusicMediaMethod:MusicMediaMethod = MusicMediaMethod.instance;

    public fun getChnListMusic(){
        val  pager: Pager<String, String>;
        val pagingConfig:PagingConfig;
    }

    val allMusicList =Pager(PagingConfig(20), initialKey = 0,){
        object: PagingSource<Long,MusicDetail>(){


            override suspend fun load(params: LoadParams<Long>): LoadResult<Long, MusicDetail> {

                KLog.d(TAG,"startLoadMusicDetail currentThread:${Thread.currentThread().name}")
                val key = params.key?:0;
                val preKey = if(key -1>=0) key-1 else null
                try {
                    var result =
                        mMusicMediaMethod.getService().getRankMusicList("kugou_8888",key.toInt(),20)
                    KLog.d(TAG,"loadResult:${result}");
                    if(result.code==0){
                        return  LoadResult.Page(result.data!!.music_list,preKey,key+1);
                    }else{
                        return   LoadResult.Error(IllegalArgumentException())
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    return LoadResult.Error(e)
                }




      /*          if (params is LoadParams.Refresh){
                   KLog.d(TAG,"startLoadMusicDetail currentThread:${Thread.currentThread().name}")
                   val key = params.key?:0;
                   val preKey = if(key -1>=0) key-1 else 0
                  try {
                   var result =
                       mMusicMediaMethod.getService().getRankMusicList("kugou_8888",key.toInt(),20)
                   if(result.code==0){
                     return  LoadResult.Page(result.data!!.music_list,preKey,key+1);
                   }else{
                    return   LoadResult.Error(IllegalArgumentException())
                   }
               }catch (e:Exception){
                  return LoadResult.Error(e)
               }

               }else if(params is LoadParams.Append){
                   val key = params.key?:0;
               }else {

               }*/
            }

            //TODO 这里暂时没有搞懂逻辑，先放着
            override fun getRefreshKey(state: PagingState<Long, MusicDetail>): Long? {
                KLog.d(TAG,"getrefreshKey: ---");
                return  null
            }
        }
    }.flow
        .cachedIn(viewModelScope)


}