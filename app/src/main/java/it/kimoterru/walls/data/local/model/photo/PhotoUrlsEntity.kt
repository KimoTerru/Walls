package it.kimoterru.walls.data.local.model.photo

import androidx.room.ColumnInfo

data class PhotoUrlsEntity(

    @ColumnInfo(name = "raw_photo_urls")
    val raw: String? = null, // ORIGINAL

    @ColumnInfo(name = "full_photo_urls")
    val full: String? = null, // JPG

    @ColumnInfo(name = "regular_photo_urls")
    val regular: String? = null, //1080

    @ColumnInfo(name = "small_photo_urls")
    val small: String? = null, //400

    @ColumnInfo(name = "thumb_photo_urls")
    val thumb: String? = null //200
)