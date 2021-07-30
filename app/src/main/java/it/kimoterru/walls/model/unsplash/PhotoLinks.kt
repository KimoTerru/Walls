package it.kimoterru.walls.model.unsplash


import com.google.gson.annotations.SerializedName

data class PhotoLinks(
    @SerializedName("download")
    val download: String,
    @SerializedName("download_location")
    val downloadLocation: String,
    @SerializedName("html")
    val html: String,
    @SerializedName("self")
    val self: String
)