package it.kimoterru.walls.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PhotoLinks(
    @ColumnInfo(name = "download_photo_links")
    @SerializedName("download")
    val download: String,

    @ColumnInfo(name = "download_location_photo_links")
    @SerializedName("download_location")
    val downloadLocation: String,

    @ColumnInfo(name = "html_photo_links")
    @SerializedName("html")
    val html: String,

    @ColumnInfo(name = "self_photo_links")
    @SerializedName("self")
    val self: String
)