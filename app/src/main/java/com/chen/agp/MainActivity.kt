package com.chen.agp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.chen.base_data.repo.LocalDBDataRepo
import com.chen.base_utils.KLog
import com.chen.base_view.viewmodel.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        LocalDBDataRepo.getInstance(getMyApplication().dataBase).getDatas().observe(this) { changedData ->
            KLog.d(TAG,"getAllDatas: ${changedData?.size}")
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel!!.viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Main.immediate){}
        }
    }


    private fun getMyApplication():BasicApp{
      return  this@MainActivity.application as BasicApp
    }
}