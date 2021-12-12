package it.kimoterru.walls.data.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Exif(
    @ColumnInfo(name = "make_cam_exif")
    @SerializedName("make")
    val make: String? = null,

    @ColumnInfo(name = "model_cam_exif")
    @SerializedName("model")
    val model: String? = null,

    /*@ColumnInfo(name = "name_cam_exif")
    @SerializedName("name")
    val name: String? = null,*/

    @ColumnInfo(name = "exposure_time_cam_exif")
    @SerializedName("exposure_time")
    val exposure_time: String? = null,

    @ColumnInfo(name = "aperture_cam_exif")
    @SerializedName("aperture")
    val aperture: String? = null,

    @ColumnInfo(name = "focal_length_cam_exif")
    @SerializedName("focal_length")
    val focal_length: String? = null,

    @ColumnInfo(name = "iso_cam_exif")
    @SerializedName("iso")
    val iso: Int? = null,
)