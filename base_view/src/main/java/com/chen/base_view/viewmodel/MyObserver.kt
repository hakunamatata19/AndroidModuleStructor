package com.chen.base_view.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.chen.base_utils.KLog

abstract class MyObserver:DefaultLifecycleObserver {

    private   val TAG = "MyObserver"

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        KLog.d(TAG,"onObserverCreate")
        onCrateCalled()
    }
    abstract fun onCrateCalled()
}