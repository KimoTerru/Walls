package it.kimoterru.walls.models.photo

import com.google.gson.annotations.SerializedName

data class ProfileImage(
    @SerializedName("large")
    val large: String, //128x128
    @SerializedName("medium")
    val medium: String, //64x64
    @SerializedName("small")
    val small: String //32x32
)