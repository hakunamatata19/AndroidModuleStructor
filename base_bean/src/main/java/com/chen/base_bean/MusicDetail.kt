package com.chen.base_bean

import com.google.gson.annotations.SerializedName

class MusicDetail constructor(
     @SerializedName("title") var song_Name: String?,
      @SerializedName("id")var song_id: String?,
     @SerializedName("singer_name")var singerName: String?,
     @SerializedName("singer_id") var singer_id: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MusicDetail

        if (song_id != other.song_id) return false

        return true
    }

    override fun hashCode(): Int {
        var result =  0
        result = 31 * result + (song_Name?.hashCode() ?: 0)
        result = 31 * result + (singerName?.hashCode() ?: 0)
        result = 31 * result + (singer_id?.hashCode() ?: 0)
        return result
    }
}