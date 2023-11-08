package com.chen.base_view.viewmodel

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chen.base_utils.KLog

/**
 * Activity 不应该自己获取数据或者控制逻辑，应该尽量保持精简
 * 应该通过ViewModel来进行数据和界面之前的动态联系
 * ViewModel并不能直接持有Activity，以避免内存泄漏
 */
abstract class BaseActivity<T> : AppCompatActivity() {
    private val TAG = "BaseActivity"
    public var mViewModel: T? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mViewModel = getViewMode()
        initView()
        initData()
    }

    override fun onStart() {
        super.onStart()
    }

    protected abstract fun getViewMode(): T
    protected abstract fun initView()
    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewMode()
        initView()
        initData()
        lifecycle.addObserver(object : MyObserver() {
            override fun onCrateCalled() {

            }

        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        KLog.d(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        KLog.d(TAG, "onSaveInstanceState. outstate:$outState, persistable:$outPersistentState")
    }

    override fun onStop() {
        KLog.d(TAG, "onActiivty stop ")
        super.onStop()
    }
}