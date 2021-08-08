package it.kimoterru.walls.models.photo

import com.google.gson.annotations.SerializedName

data class  PhotoUrls(
    @SerializedName("full")
    val full: String,
    @SerializedName("raw")
    val raw: String,
    @SerializedName("regular")
    val regular: String, //1080
    @SerializedName("small")
    val small: String, //400
    @SerializedName("thumb")
    val thumb: String //200
)