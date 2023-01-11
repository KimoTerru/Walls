package it.kimoterru.walls.data.local.model.photo

import androidx.room.ColumnInfo

data class PhotoLinksEntity(

    @ColumnInfo(name = "self_photo_links")
    val self: String? = null,

    @ColumnInfo(name = "html_photo_links")
    val html: String? = null,

    @ColumnInfo(name = "download_photo_links")
    val download: String? = null,

    @ColumnInfo(name = "download_location_photo_links")
    val downloadLocation: String? = null,
)