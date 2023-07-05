package com.chen.agp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.chen.base_utils.KLog
import com.chen.base_view.viewmodel.BaseActivity

class MainActivity : BaseActivity<MyViewModel>() {
    private   val TAG = "MainActivity"
    private lateinit var  mContainer:ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewMode(): MyViewModel {
        val store :ViewModelStoreOwner? =null
         return ViewModelProvider(this@MainActivity).get(MyViewModel::class.java)
    }

    @SuppressLint("MissingInflatedId")
    override fun initView() {
        KLog.d(TAG,"hhe");
        setContentView(R.layout.activity_main)
        mContainer=findViewById(R.id.main_layout_container)
    }

    override fun initData() {

    }
}