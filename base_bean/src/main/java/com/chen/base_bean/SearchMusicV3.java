package com.chen.base_bean;


import com.alibaba.fastjson2.annotation.JSONField;

import java.util.List;

/**
 * @author coocaa
 * @name Karaoke
 * @package name：com.coocaa.karaoke.data.bean
 * @time 6/5/2021 下午 3:15
 * @chang time
 * @describe 对接小维语音V3版本，媒资搜索接口。
 */
public class SearchMusicV3 {

    //返回类型，1-歌曲列表，2-排行榜，3-专辑
  @JSONField(name = "type")
    public int type = 1;

    @JSONField(name = "ranking_info")
    public RankingInfo mRankInfo = null;

    @JSONField(name = "total")
    public int total;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RankingInfo getmRankInfo() {
        return mRankInfo;
    }

    public void setmRankInfo(RankingInfo mRankInfo) {
        this.mRankInfo = mRankInfo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<MusicDetail> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<MusicDetail> musicList) {
        this.musicList = musicList;
    }

    @JSONField(name = "last_page")
    public boolean lastPage;

    @JSONField(name = "page_index")
    public int pageIndex;

    @JSONField(name = "music_list")
    public List<MusicDetail> musicList;

    public class RankingInfo {
        @JSONField(name = "id")
        public int rankId = 0;

        @JSONField(name = "title")
        public String title = "";
    }


}
