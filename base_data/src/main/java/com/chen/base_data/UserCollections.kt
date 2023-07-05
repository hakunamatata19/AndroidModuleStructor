package com.chen.base_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chen.base_bean.BaseSong
import com.chen.base_bean.BeanConstant
import com.chen.base_bean.CollectionSong
import com.chen.base_bean.PartCollectionSong
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCollections {

    @Query("SELECT * From song_collect")
    fun getAll():List<CollectionSong>


    @Query("SELECT * From song_collect")
    fun getAllInMain(): Flow<List<CollectionSong>>

    @Query("SELECT * From song_collect")
    fun getAllSongDetail():LiveData<List<CollectionSong>>

    @Query("SELECT * From song_collect WHERE songId = (:sId) ")
    fun getSongById(sId:String):CollectionSong

    @Query("SELECT * From song_collect WHERE songId = (:sId) ")
    fun getSongByIdAsync(sId:String):LiveData<CollectionSong>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertAll(vararg detail:CollectionSong)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle( song:CollectionSong)


    /**如果使用Delete字段，一种方案就是直接拿到对应的Item来进行删除，**/
    @Delete
    fun deleteSong(song:CollectionSong)

    /***如果只知道删除对象的一部分，那么可以使用 Entity的部分参数进行删除*/
    @Delete(entity = CollectionSong::class)
    fun deleteSongSpec(song:PartCollectionSong)

    //@Query(" DELETE FROM (${BeanConstant.TABLE_COLLECT}) WHERE   type  =:type")
    @Query("Delete  From song_collect WHERE addType = (:type) ")
    fun clearAllSong(type:String)

}