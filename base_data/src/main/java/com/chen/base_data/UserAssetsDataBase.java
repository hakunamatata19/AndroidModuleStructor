package com.chen.base_data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.chen.base_bean.BeanConstant;
import com.chen.base_bean.CollectionSong;
import com.chen.base_utils.AppExecutors;
import com.chen.base_utils.KLog;

@Database(entities = {CollectionSong.class},version = 2,exportSchema = false)
public abstract class UserAssetsDataBase extends RoomDatabase {
    private static final String TAG = "UserAssetsDataBase";
    private static UserAssetsDataBase sInstance;

    @VisibleForTesting
    public static final String DB_NAME = "user_assets";

    public abstract  UserCollections getCollectionDao();

    private final MutableLiveData<Boolean> mIsDbCreated= new MutableLiveData<>();

    public static UserAssetsDataBase getInstance(final Context ctx, final AppExecutors execuators){
        if(sInstance==null){
            synchronized (UserAssetsDataBase.class){
                if(sInstance==null){
                    KLog.d(TAG,"startBuildDatabase---");
                    sInstance = buildDataBase(ctx,execuators);
                    KLog.d(TAG,"updateDataBaseCreated");
                    sInstance.updateDatabaseCreated(ctx.getApplicationContext());
                }
            }

        }
        return sInstance;
    }
    private void setDatabaseCreated(){
        mIsDbCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(BeanConstant.USER_ASSETS_DB_NAME).exists()) {
            setDatabaseCreated();
        }
    }
    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDbCreated;
    }
    private static UserAssetsDataBase buildDataBase(final Context context, final AppExecutors exec){
        KLog.d(TAG,"startBuildDatabse---");
        RoomDatabase.Builder<UserAssetsDataBase>    builder =
                Room.databaseBuilder(context, UserAssetsDataBase.class, BeanConstant.USER_ASSETS_DB_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(
                new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        KLog.d(TAG,"onDataCreated "+Thread.currentThread().getName());
                        UserAssetsDataBase database = UserAssetsDataBase.getInstance(context, exec);
                        database.setDatabaseCreated();
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        KLog.d(TAG,"onOpen");
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        super.onDestructiveMigration(db);
                        KLog.d(TAG,"ondestr");
                    }
                }
        ) ;
        KLog.d(TAG,"startBuild...");
        return  builder.build();
    }


}
