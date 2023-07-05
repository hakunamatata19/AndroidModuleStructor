package com.chen.base_bean;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = BeanConstant.TABLE_COLLECT)
public class CollectionSong extends BaseSong {


    private long addTime;
    private String addType;
    @Ignore
    private long duration;

    /**制定是哪个用户进行收藏的*/
    private String userId="";

    public boolean isUpdateToServer() {
        return updateToServer;
    }

    public void setUpdateToServer(boolean updateToServer) {
        this.updateToServer = updateToServer;
    }

    public boolean isLogicalDelete() {
        return isLogicalDelete;
    }

    public void setLogicalDelete(boolean logicalDelete) {
        isLogicalDelete = logicalDelete;
    }

    /**是否需要将当前数据上报到服务器 true表示需要，false表示不需要*/
    private  boolean updateToServer= true;

    /**表示当前数据是否需要进行删除，如果 一个用户反复添加删除某一条数据，可以使用这个*/
    private  boolean isLogicalDelete=  false;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getAddType() {
        return addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
