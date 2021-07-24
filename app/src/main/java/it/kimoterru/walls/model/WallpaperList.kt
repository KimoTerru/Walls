package it.kimoterru.walls.model

import com.google.gson.annotations.SerializedName

data class WallpaperList(
    @SerializedName("data")
    private var data: List<Data?>,
    @SerializedName("meta")
    private val meta: Meta,
)