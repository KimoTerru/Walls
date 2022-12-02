package it.kimoterru.walls.data.remote.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class ProfileImageResponse(
    @ColumnInfo(name = "large_photoImage")
    @SerializedName("large")
    val large: String? = null, //128x128

    @ColumnInfo(name = "medium_photoImage")
    @SerializedName("medium")
    val medium: String? = null, //64x64

    @ColumnInfo(name = "small_photoImage")
    @SerializedName("small")
    val small: String? = null //32x32
)