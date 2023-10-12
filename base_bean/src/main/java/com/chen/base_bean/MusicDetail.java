package com.chen.base_bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MusicDetail {

    @SerializedName("action")
    private String action;
    @SerializedName("album_id")
    private String albumId;
    @SerializedName("album_title")
    private String albumTitle;
    @SerializedName("bg_poster")
    private String bgPoster;
    @SerializedName("charge_type")
    private Integer chargeType;
    @SerializedName("chorus_id")
    private String chorusId;
    @SerializedName("chorus_pic")
    private String chorusPic;
    @SerializedName("chorus_vip")
    private Integer chorusVip;
    @SerializedName("coocaa_s_id")
    private String coocaaSId;
    @SerializedName("corner_icons")
    private List<?> cornerIcons;
    @SerializedName("cover")
    private String cover;
    @SerializedName("duration")
    private Integer duration;
    @SerializedName("focus_color")
    private String focusColor;
    @SerializedName("id")
    private String id;
    @SerializedName("images")
    private List<ImagesDTO> images;
    @SerializedName("is_chorus")
    private Integer isChorus;
    @SerializedName("is_combine_singer")
    private Integer isCombineSinger;
    @SerializedName("is_mv")
    private Integer isMv;
    @SerializedName("m_id")
    private String mId;
    @SerializedName("mv_id")
    private String mvId;
    @SerializedName("mv_url")
    private String mvUrl;
    @SerializedName("order_number")
    private String orderNumber;
    @SerializedName("origin_id")
    private String originId;
    @SerializedName("playable_code")
    private Integer playableCode;
    @SerializedName("preview_type")
    private Integer previewType;
    @SerializedName("singer_id")
    private String singerId;
    @SerializedName("singer_infos")
    private List<SingerInfosDTO> singerInfos;
    @SerializedName("singer_name")
    private String singerName;
    @SerializedName("song_size")
    private Integer songSize;
    @SerializedName("song_size_pq")
    private Integer songSizePq;
    @SerializedName("song_size_sq")
    private Integer songSizeSq;
    @SerializedName("source")
    private String source;
    @SerializedName("source_name")
    private String sourceName;
    @SerializedName("source_sign")
    private String sourceSign;
    @SerializedName("support_qualities")
    private String supportQualities;
    @SerializedName("text_color")
    private String textColor;
    @SerializedName("title")
    private String title;
    @SerializedName("try_begin_pos")
    private Integer tryBeginPos;
    @SerializedName("try_end_pos")
    private Integer tryEndPos;
    @SerializedName("url")
    private String url;
    @SerializedName("url_type")
    private String urlType;


    public static class ImagesDTO {
        @SerializedName("image_adaption")
        private Object imageAdaption;
        @SerializedName("pic_type")
        private String picType;
        @SerializedName("picture_color")
        private String pictureColor;
        @SerializedName("size")
        private String size;
        @SerializedName("style")
        private String style;
        @SerializedName("url")
        private String url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getBgPoster() {
        return bgPoster;
    }

    public void setBgPoster(String bgPoster) {
        this.bgPoster = bgPoster;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public String getChorusId() {
        return chorusId;
    }

    public void setChorusId(String chorusId) {
        this.chorusId = chorusId;
    }

    public String getChorusPic() {
        return chorusPic;
    }

    public void setChorusPic(String chorusPic) {
        this.chorusPic = chorusPic;
    }

    public Integer getChorusVip() {
        return chorusVip;
    }

    public void setChorusVip(Integer chorusVip) {
        this.chorusVip = chorusVip;
    }

    public String getCoocaaSId() {
        return coocaaSId;
    }

    public void setCoocaaSId(String coocaaSId) {
        this.coocaaSId = coocaaSId;
    }

    public List<?> getCornerIcons() {
        return cornerIcons;
    }

    public void setCornerIcons(List<?> cornerIcons) {
        this.cornerIcons = cornerIcons;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getFocusColor() {
        return focusColor;
    }

    public void setFocusColor(String focusColor) {
        this.focusColor = focusColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImagesDTO> getImages() {
        return images;
    }

    public void setImages(List<ImagesDTO> images) {
        this.images = images;
    }

    public Integer getIsChorus() {
        return isChorus;
    }

    public void setIsChorus(Integer isChorus) {
        this.isChorus = isChorus;
    }

    public Integer getIsCombineSinger() {
        return isCombineSinger;
    }

    public void setIsCombineSinger(Integer isCombineSinger) {
        this.isCombineSinger = isCombineSinger;
    }

    public Integer getIsMv() {
        return isMv;
    }

    public void setIsMv(Integer isMv) {
        this.isMv = isMv;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getMvId() {
        return mvId;
    }

    public void setMvId(String mvId) {
        this.mvId = mvId;
    }

    public String getMvUrl() {
        return mvUrl;
    }

    public void setMvUrl(String mvUrl) {
        this.mvUrl = mvUrl;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public Integer getPlayableCode() {
        return playableCode;
    }

    public void setPlayableCode(Integer playableCode) {
        this.playableCode = playableCode;
    }

    public Integer getPreviewType() {
        return previewType;
    }

    public void setPreviewType(Integer previewType) {
        this.previewType = previewType;
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public List<SingerInfosDTO> getSingerInfos() {
        return singerInfos;
    }

    public void setSingerInfos(List<SingerInfosDTO> singerInfos) {
        this.singerInfos = singerInfos;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getSongSize() {
        return songSize;
    }

    public void setSongSize(Integer songSize) {
        this.songSize = songSize;
    }

    public Integer getSongSizePq() {
        return songSizePq;
    }

    public void setSongSizePq(Integer songSizePq) {
        this.songSizePq = songSizePq;
    }

    public Integer getSongSizeSq() {
        return songSizeSq;
    }

    public void setSongSizeSq(Integer songSizeSq) {
        this.songSizeSq = songSizeSq;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceSign() {
        return sourceSign;
    }

    public void setSourceSign(String sourceSign) {
        this.sourceSign = sourceSign;
    }

    public String getSupportQualities() {
        return supportQualities;
    }

    public void setSupportQualities(String supportQualities) {
        this.supportQualities = supportQualities;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTryBeginPos() {
        return tryBeginPos;
    }

    public void setTryBeginPos(Integer tryBeginPos) {
        this.tryBeginPos = tryBeginPos;
    }

    public Integer getTryEndPos() {
        return tryEndPos;
    }

    public void setTryEndPos(Integer tryEndPos) {
        this.tryEndPos = tryEndPos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public static class SingerInfosDTO {
        @SerializedName("coocaa_s_id")
        private String coocaaSId;
        @SerializedName("singer_id")
        private String singerId;
        @SerializedName("singer_name")
        private String singerName;
    }
}
