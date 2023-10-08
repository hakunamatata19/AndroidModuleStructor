package com.chen.base_bean

class MusicDetail constructor(
    private var title: String?,
    private var songName: String?,
    private var song_id: String?,
    private var singerName: String?,
    private var singer_id: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MusicDetail

        if (song_id != other.song_id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (songName?.hashCode() ?: 0)
        result = 31 * result + (singerName?.hashCode() ?: 0)
        result = 31 * result + (singer_id?.hashCode() ?: 0)
        return result
    }
}