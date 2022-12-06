package it.kimoterru.walls.data.remote.models.photo

import com.google.gson.annotations.SerializedName

data class ExifResponse(
    @SerializedName("make")
    val make: String? = null,

    @SerializedName("model")
    val model: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("exposure_time")
    val exposure_time: String? = null,

    @SerializedName("aperture")
    val aperture: String? = null,

    @SerializedName("focal_length")
    val focal_length: String? = null,

    @SerializedName("iso")
    val iso: Int? = null,
)