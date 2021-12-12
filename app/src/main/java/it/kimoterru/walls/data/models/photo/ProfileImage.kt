package it.kimoterru.walls.data.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class ProfileImage(
    @ColumnInfo(name = "large_photoImage")
    @SerializedName("large")
    val large: String, //128x128

    @ColumnInfo(name = "medium_photoImage")
    @SerializedName("medium")
    val medium: String, //64x64

    @ColumnInfo(name = "small_photoImage")
    @SerializedName("small")
    val small: String //32x32
)