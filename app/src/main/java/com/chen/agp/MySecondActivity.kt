package com.chen.agp

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.chen.base_data.UserAssetsDataBase
import com.chen.base_data.repo.LocalDBDataRepo
import com.chen.base_utils.KLog
import com.chen.base_view.viewmodel.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MySecondActivity : BaseActivity<SecondViewModel>(), View.OnClickListener {
    private val TAG = "MySecondActivity"
    //   lateinit var   viewModel:SecondViewModel

    private lateinit var mBtnAdd: AppCompatButton
    private lateinit var mBtnClear: AppCompatButton
    private lateinit var mBtnCheck: AppCompatButton
    private lateinit var mBtnNewActivity: AppCompatButton
    private val REQUEST_CODE_SAVE_IMAGE_FILE = 110

    override fun getViewMode(): SecondViewModel {
        return ViewModelProvider(this@MySecondActivity).get(SecondViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initView() {
        setContentView(R.layout.second_activity)
        mBtnAdd = findViewById(R.id.second_btn_add)
        mBtnClear = findViewById(R.id.second_btn_delete)
        mBtnCheck = findViewById(R.id.second_btn_request)
        mBtnNewActivity = findViewById(R.id.second_btn_newActivity)

        mBtnAdd.setOnClickListener(this@MySecondActivity)
        mBtnClear.setOnClickListener(this@MySecondActivity)
        mBtnCheck.setOnClickListener(this@MySecondActivity)
        mBtnNewActivity.setOnClickListener(this@MySecondActivity)
    }

    override fun initData() {
        LocalDBDataRepo.getInstance(getMyApplication().dataBase).getDatas()
            .observe(this) { changedData ->
                KLog.d(TAG, "getAllDatas: ${changedData?.size}")
            }
    }


    private fun getMyApplication(): BasicApp {
        return this@MySecondActivity.application as BasicApp
    }

    override fun onResume() {
        super.onResume()
        KLog.d(TAG, "onResume---");
        if (ContextCompat.checkSelfPermission(
                this@MySecondActivity,
                Manifest.permission.FOREGROUND_SERVICE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            KLog.d(TAG, "没有启动权限。");
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MySecondActivity,
                    Manifest.permission.FOREGROUND_SERVICE
                )
            ) {
                KLog.d(TAG, "请求权限。");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this@MySecondActivity.requestPermissions(
                        arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_SAVE_IMAGE_FILE
                    )
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    KLog.d(TAG, "开始请求权限---");
                    this@MySecondActivity.requestPermissions(
                        arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_CODE_SAVE_IMAGE_FILE
                    )
                }
            }
        }

        val jvmName = System.getProperty("java.vm.name")
        val osName = System.getProperty("os.name")
        KLog.d(TAG,"jvmName:$jvmName, osName:$osName");
        System.getProperties().forEach{
            entry->
            KLog.d(TAG,"JavaSystemProp: ${entry?.key},value:${entry?.value}");
        }
        KLog.d(TAG, "trystartIntent");
    /*    val intent = Intent()
         intent.setComponent(ComponentName("com.coocaa.karaoke","com.coocaa.karaoke.component.SkyVoiceProcessor"))
        intent.putExtra("target_page", "page_music_search")
        intent.putExtra("content_main", "search_play_ksong")
        intent.putExtra("content_subordinate", "{}")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            KLog.d(TAG, "currentVersion isBiggerThanAndorid O");
            val component = startForegroundService(intent)
            KLog.d(TAG, "startService----$component");

        }*/


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_SAVE_IMAGE_FILE) {
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

    override fun onPause() {
        super.onPause()
        mViewModel?.rollDice()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.second_btn_add -> {
                mViewModel?.getMusicDetail("19")
                if (ContextCompat.checkSelfPermission(
                        this@MySecondActivity,
                        Manifest.permission.FOREGROUND_SERVICE
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    KLog.d(TAG, "没有启动权限。");
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this@MySecondActivity,
                            Manifest.permission.FOREGROUND_SERVICE
                        )
                    ) {
                        KLog.d(TAG, "请求权限。");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            this@MySecondActivity.requestPermissions(
                                arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                REQUEST_CODE_SAVE_IMAGE_FILE
                            )
                        }
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            KLog.d(TAG, "开始请求权限---");
                            this@MySecondActivity.requestPermissions(
                                arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                REQUEST_CODE_SAVE_IMAGE_FILE
                            )
                        }
                    }
                }else{
                    KLog.d(TAG,"permissionConfirm");
                }

           /*     am start-foreground-service -a coocaa.intent.music.ACTION.VOICE_CONTROL
                --es target_page "page_music_search"
                --es content_main "search_play_ksong"
                --es content_subordinate "{}"*/
                KLog.d(TAG, "trystartIntent");
               /* val intent = Intent()
                 intent.setComponent(ComponentName("com.coocaa.karaoke","com.coocaa.karaoke.component.SkyVoiceProcessor"))
                intent.putExtra("target_page", "page_music_search")
                intent.putExtra("content_main", "search_play_ksong")
                intent.putExtra("content_subordinate", "{}")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    KLog.d(TAG, "currentVersion isBiggerThanAndorid O");
                    val component = startForegroundService(intent)
                    KLog.d(TAG, "startService----$component");

                }*/

               /* mViewModel?.viewModelScope?.launch(Dispatchers.IO) {
                    val datas = ArrayList<CollectionSong>()
                    for (x in 0..50) {
                        val song = CollectionSong()
                        song.songName = "song$x"
                        song.songTitle = "songTitle$x"
                        song.songId = "songId$x"
                        song.singerName = "singer$x"
                        song.albumName = "albume$x"
                        song.addTime = System.currentTimeMillis()
                        song.addType = "collection"
                        song.userId = "UserId_chen"
                        datas.add(song)

                    }
                    UserAssetsDataBase.getInstance(
                        this@MySecondActivity,
                        (this@MySecondActivity.application as BasicApp).getmAppExecutors()
                    )
                        .collectionDao.insertAll(datas)
                }*/
            }

            R.id.second_btn_delete -> {
                lifecycleScope.launch(Dispatchers.IO) {
                    UserAssetsDataBase.getInstance(
                        this@MySecondActivity,
                        (this@MySecondActivity.application as BasicApp).getmAppExecutors()
                    )
                        .collectionDao.clearAllSong("collection")
                }
            }

            R.id.second_btn_request -> {
                lifecycleScope.launch {
                    UserAssetsDataBase.getInstance(
                        this@MySecondActivity,
                        (this@MySecondActivity.application as BasicApp).getmAppExecutors()
                    )
                        .collectionDao.getAllInMain().collect() {
                            KLog.d(TAG, "onSongCollected:${it.size}")
                        }
                }
            }
            R.id.second_btn_newActivity->{

                startActivity(Intent().apply {
                    KLog.d(TAG,"com.agp.chen.action.PAGING ${PagingDataActivity::class}")
                    KLog.d(TAG,"com.agp.chen.action.PAGING ${PagingDataActivity::javaClass}")
                    KLog.d(TAG,"com.agp.chen.action.PAGING ${PagingDataActivity::class.java}")
                    KLog.d(TAG,"com.agp.chen.action.PAGING ${PagingDataActivity::class.javaObjectType}")
                    setAction("com.agp.chen.action.PAGING")
                    setClass(this@MySecondActivity,PagingDataActivity::class.java)
                })
            }
        }
    }
}