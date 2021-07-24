package it.kimoterru.walls.model

import com.google.gson.annotations.SerializedName

data class Thumbs(

    @SerializedName("large")
    val large: String,

    @SerializedName("original")
    val original: String,

    @SerializedName("small")
    val small: String

)