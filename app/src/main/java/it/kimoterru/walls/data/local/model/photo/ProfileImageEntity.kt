package it.kimoterru.walls.data.local.model.photo

import androidx.room.ColumnInfo

data class ProfileImageEntity(
    @ColumnInfo(name = "large_photoImage")
    val large: String? = null, //128x128

    @ColumnInfo(name = "medium_photoImage")
    val medium: String? = null, //64x64

    @ColumnInfo(name = "small_photoImage")
    val small: String? = null //32x32
)