package it.kimoterru.walls.data.remote.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PhotoUrlsResponse(

    @ColumnInfo(name = "raw_photo_urls")
    @SerializedName("raw")
    val raw: String? = null, // ORIGINAL

    @ColumnInfo(name = "full_photo_urls")
    @SerializedName("full")
    val full: String? = null, // JPG

    @ColumnInfo(name = "regular_photo_urls")
    @SerializedName("regular")
    val regular: String? = null, //1080

    @ColumnInfo(name = "small_photo_urls")
    @SerializedName("small")
    val small: String? = null, //400

    @ColumnInfo(name = "thumb_photo_urls")
    @SerializedName("thumb")
    val thumb: String ? = null //200
)