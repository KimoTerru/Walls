package it.kimoterru.walls.model

import com.google.gson.annotations.SerializedName

data class Wallpaper(
    @SerializedName("data")
    val data: List<Data>
)