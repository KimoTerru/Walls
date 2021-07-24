package it.kimoterru.walls.model

import com.google.gson.annotations.SerializedName

data class Avatar(

    @SerializedName("200px")
    val the200Px: String,

    @SerializedName("128px")
    val the128Px: String,

    @SerializedName("32px")
    val the32Px: String,

    @SerializedName("20px")
    val the20Px: String

)