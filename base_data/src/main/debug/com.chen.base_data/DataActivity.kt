package com.chen.base_data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chen.base_utils.KLog

class DataActivity : AppCompatActivity() {
    private   val TAG = "DataActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        KLog.d(TAG,"currentActivity created.");
        super.onCreate(savedInstanceState)
    }
}