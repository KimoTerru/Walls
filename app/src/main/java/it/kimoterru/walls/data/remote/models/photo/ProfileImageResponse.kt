package it.kimoterru.walls.data.remote.models.photo

import com.google.gson.annotations.SerializedName

data class ProfileImageResponse(
    @SerializedName("large")
    val large: String? = null, //128x128

    @SerializedName("medium")
    val medium: String? = null, //64x64

    @SerializedName("small")
    val small: String? = null //32x32
)