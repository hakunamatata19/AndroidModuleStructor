package com.chen.base_http.base

import android.text.TextUtils
import com.chen.base_utils.KLog
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract  class BaseHttpMethod<T>{
      var mService: T

    protected abstract fun getBaseUrl(): String?

    protected  abstract fun getTimeOut(): Int

    protected abstract fun getServiceClazz(): Class<T>?

    protected abstract fun getHeaders(): Map<String, String>?


    open fun printLog(): Boolean {
            return false;
    }


    private   fun getJsonConverterFactory(): Converter.Factory? {
         // GsonConverterFactory.create()
        return MyGsonConverterFactory.instance
    }

    constructor() {
        val client = getClient()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(getBaseUrl())
            .addCallAdapterFactory(HttpResultCallAdapterFactory.create())
            .addConverterFactory(getJsonConverterFactory()) //添加Gson
            .build()
        mService = retrofit.create(getServiceClazz())
    }

     constructor(baseUrl: String) {
        if (TextUtils.isEmpty(baseUrl)) throw RuntimeException("baseUrl cannot be empty.")
        val client = getClient()
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(if (baseUrl.endsWith("/")) baseUrl else "$baseUrl/")
            .addConverterFactory(getJsonConverterFactory())
            .build()
        mService = retrofit.create(getServiceClazz())
    }

      constructor(headersMap: Map<String, String>?) {
        val client = getClient(headersMap)
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(getBaseUrl())
            .addConverterFactory(getJsonConverterFactory())
            .build()
        mService = retrofit.create(getServiceClazz())
    }

    protected open fun getClient(): OkHttpClient {
        return getClient(getHeaders())
    }

    private   fun getClient(headers: Map<String, String>?): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        //httpClientBuilder.addInterceptor(DomainIntercept())
        httpClientBuilder.addInterceptor(Interceptor { chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            try {
                if ( headers?.isNotEmpty()==true) {
                    val iterator: Iterator<*> = headers.entries.iterator()
                    while (iterator.hasNext()) {
                        val (key, value) = iterator.next() as Map.Entry<*, *>
                        (key as String?)?.let { (value as String?)?.let { it1 ->
                            requestBuilder.addHeader(it,
                                it1
                            )
                        } }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            chain.proceed(requestBuilder.build())
        })
        httpClientBuilder.connectTimeout(getTimeOut().toLong(), TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(getTimeOut().toLong(), TimeUnit.SECONDS)
        if (printLog()) {
            val loggingInterceptor = HttpLoggingInterceptor(HomeHttpLogger())
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return httpClientBuilder.build();
    }


    class HomeHttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            KLog.i("OkHttp", message)
        }
    }

    open fun getService(): T {
        return mService
    }





}