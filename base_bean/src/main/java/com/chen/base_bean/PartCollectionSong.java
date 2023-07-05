package com.chen.base_bean;

import androidx.room.Ignore;

public class PartCollectionSong {

    private String addType;
    @Ignore
    private long duration;

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
