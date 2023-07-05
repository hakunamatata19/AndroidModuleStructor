package com.chen.base_data

import android.app.Application
import com.chen.base_utils.KLog

class DataApplication :Application() {
    private   val TAG = "DataApplication"
    override fun onCreate() {
        KLog.d(TAG,"onApplicationCreated.");
        super.onCreate()
    }
}