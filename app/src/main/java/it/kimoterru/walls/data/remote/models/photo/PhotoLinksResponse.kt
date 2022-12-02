package it.kimoterru.walls.data.remote.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PhotoLinksResponse(

    @ColumnInfo(name = "self_photo_links")
    @SerializedName("self")
    val self: String? = null,

    @ColumnInfo(name = "html_photo_links")
    @SerializedName("html")
    val html: String? = null,

    @ColumnInfo(name = "download_photo_links")
    @SerializedName("download")
    val download: String? = null,

    @ColumnInfo(name = "download_location_photo_links")
    @SerializedName("download_location")
    val downloadLocation: String? = null,
)