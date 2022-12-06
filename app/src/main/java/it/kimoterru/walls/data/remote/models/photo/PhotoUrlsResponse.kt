package it.kimoterru.walls.data.remote.models.photo

import com.google.gson.annotations.SerializedName

data class PhotoUrlsResponse(

    @SerializedName("raw")
    val raw: String? = null, // ORIGINAL

    @SerializedName("full")
    val full: String? = null, // JPG

    @SerializedName("regular")
    val regular: String? = null, //1080

    @SerializedName("small")
    val small: String? = null, //400

    @SerializedName("thumb")
    val thumb: String? = null //200
)