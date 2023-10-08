package com.chen.base_http.business

import com.chen.base_http.BuildConfig
import com.chen.base_http.base.HttpMethod2

class MusicMediaMethod :HttpMethod2<MusicMediaService>() {

    companion object {
        val instance by lazy (LazyThreadSafetyMode.SYNCHRONIZED){
            MusicMediaMethod()
        }
    }

    override fun getBaseUrl(): String? {
        return BuildConfig.MUSIC_MEDIA_INFO_URL
    }

    override fun getTimeOut(): Int {
       return 10000
    }

    override fun getServiceClazz(): Class<MusicMediaService>? {
         return MusicMediaService::class.java
    }

    override fun getHeaders(): Map<String, String>? {
         return HashMap<String, String>().apply {
         }
    }
}