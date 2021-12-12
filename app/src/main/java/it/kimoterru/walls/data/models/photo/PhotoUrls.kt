package it.kimoterru.walls.data.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PhotoUrls(
    @ColumnInfo(name = "full_photo_urls")
    @SerializedName("full")
    val full: String,

    @ColumnInfo(name = "raw_photo_urls")
    @SerializedName("raw")
    val raw: String,

    @ColumnInfo(name = "regular_photo_urls")
    @SerializedName("regular")
    val regular: String, //1080

    @ColumnInfo(name = "small_photo_urls")
    @SerializedName("small")
    val small: String, //400

    @ColumnInfo(name = "thumb_photo_urls")
    @SerializedName("thumb")
    val thumb: String //200
)