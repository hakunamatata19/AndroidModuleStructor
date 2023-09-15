package com.chen.agp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.chen.base_data.repo.LocalDBDataRepo
import com.chen.base_utils.KLog
import com.chen.base_view.viewmodel.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.TreeMap
import java.util.concurrent.ThreadPoolExecutor

class MainActivity : BaseActivity<MyViewModel>() {
    private   val TAG = "MainActivity"
    private lateinit var  mContainer:ConstraintLayout

    private lateinit var  mLocalAssetsManager:AssetManager
    private val REQUEST_CODE_SAVE_IMAGE_FILE = 110
    override fun onCreate(savedInstanceState: Bundle?) {
        KLog.d(TAG,"onActivityCreate....");
        super.onCreate(savedInstanceState)
    }

    override fun getViewMode(): MyViewModel {
         return ViewModelProvider(this@MainActivity).get(MyViewModel::class.java)
    }

    @SuppressLint("MissingInflatedId")
    override fun initView() {
        KLog.d(TAG,"hhe");
        val treeMap:TreeMap<String,String> ;
        val threadPool:ThreadPoolExecutor;
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
        var view: View ;

        if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.FOREGROUND_SERVICE)
            != PackageManager.PERMISSION_GRANTED) {
            KLog.d(TAG,"没有启动权限。");
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.FOREGROUND_SERVICE
                )
            ) {
                KLog.d(TAG,"请求权限。");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this@MainActivity.requestPermissions(
                        arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_SAVE_IMAGE_FILE
                    )
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    KLog.d(TAG,"开始请求权限---");
                    this@MainActivity.requestPermissions(
                        arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_SAVE_IMAGE_FILE
                    )
                }
            }
            return
        }

        mViewModel!!.viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Main.immediate){
                val intent = Intent();
                intent.setAction("coocaa.intent.music.ACTION.VOICE_CONTROL")
                intent.putExtra("target_page","page_music_search")
                intent.putExtra("content_main","search_play_ksong")
                intent.putExtra("content_subordinate","{}")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    KLog.d(TAG,"currentVersion isBiggerThanAndorid O");
                    startForegroundService(intent)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode ==  REQUEST_CODE_SAVE_IMAGE_FILE) {
            for (i in permissions.indices) {
                val permissionGranted = grantResults[i] == PackageManager.PERMISSION_GRANTED
                KLog.d(
                 TAG,
                    "申请的权限为：" + permissions[i] + ",申请结果：" +
                            permissionGranted
                )
                if (!permissionGranted) {
                    KLog.w(
                       TAG,
                        "current record is closed. "
                    )

                }
            }
        }

    }
    private fun getMyApplication():BasicApp{
      return  this@MainActivity.application as BasicApp
    }
}