/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chen.agp;

import android.content.Context;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.chen.agp.db.AppDatabase;
import com.chen.base_data.UserAssetsDataBase;
import com.chen.base_utils.AppExecutors;
import com.chen.base_utils.KLog;

/**
 * Android Application class. Used for accessing singletons.
 */
public class BasicApp extends MultiDexApplication {
    private static final String TAG = "BasicApp";
    private AppExecutors mAppExecutors;

    @Override
    protected void attachBaseContext(Context base) {

        super.attachBaseContext(base);
        MultiDex.install(this);
        KLog.d(TAG,"startattachBaseContext"+ BuildConfig.DEBUG);
    }
    private UserAssetsDataBase mUserAssetsDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
        AppDatabase.getInstance(this,mAppExecutors);
        mUserAssetsDatabase=UserAssetsDataBase.getInstance(this,mAppExecutors);
        mUserAssetsDatabase.getDatabaseCreated().observeForever(change->{
            KLog.d(TAG,"onDataBaseCreate");

        });
    }

    public AppExecutors getmAppExecutors() {
        return mAppExecutors;
    }

    public UserAssetsDataBase getDataBase(){
        return  mUserAssetsDatabase;
    }
}
