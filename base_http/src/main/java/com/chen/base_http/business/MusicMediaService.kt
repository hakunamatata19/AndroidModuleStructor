package com.chen.base_http.business

import com.chen.base_bean.HttpResult
import com.chen.base_bean.MusicDetail
import com.chen.base_bean.SearchMusicV3
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface MusicMediaService {
    /**
     * 获取分类的接口
     * http://wiki.skyoss.com/pages/viewpage.action?pageId=38769467
     * <p>
     * 二、返回格式
     * 　　回包格式
     * 字段名	　	　	数据类型	说明
     * code	　	　	　	int	返回状态码。0表示成功；其他大于0的值表示请求失败。
     * msg	　	　	　	string	请求结果描述信息
     * data	　	　	　	object	分类列表数据
     * 　	type	　	　	int	分类数据类型，0-歌曲分类，1-歌手分类
     * chn_list	 	 	array	分类信息集合
     * chn_id	 	string	分类ID
     * 　	 	chn_name	　	string	分类名称
     * 　	 	filters	　	string	过滤条件
     * 　	 	sorts	　	string	排序条件
     *
     * @param type 0表示歌曲分类， 1表示歌手分类 接口数据需要使用URLEncode进行编码
     * @return
     */
    @GET("ms/getChnList")
   public fun  getChnList(@Query ("type")type:Int,@Query("category_id" )category_id:String): HttpResult<Object>


    /**
     * http://beta.movie.tc.skysrt.com/ms/getMusicDetail?id=19
     * http://movie.tc.skysrt.com/ms/getMusicDetail?id=xxx
     * 获取歌手内容接口
     *
     * @param id
     * @return
     */
    @GET("ms/getMusicDetail")
    fun getMusicDetail(@Query("id") id: String?): HttpResult<MusicDetail?>?


    /**
     * 搜索歌曲 http://wiki.skyoss.com/pages/viewpage.action?pageId=46830062
     *
     * @ song array
     * @ singer array
     * @ tag array  过滤条件
     * @ album array
     * @ pageInde
     * @ pageSize
     * @
     */
    @POST("ms/speechSearch")
    fun searchMusicV2(@Body body: RequestBody?): HttpResult<SearchMusicV3?>?
}