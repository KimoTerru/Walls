package it.kimoterru.walls.data.local.model.photo

import androidx.room.ColumnInfo

data class ExifEntity(
    @ColumnInfo(name = "make_cam_exif")
    val make: String? = null,

    @ColumnInfo(name = "model_cam_exif")
    val model: String? = null,

    @ColumnInfo(name = "name_cam_exif")
    val name: String? = null,

    @ColumnInfo(name = "exposure_time_cam_exif")
    val exposure_time: String? = null,

    @ColumnInfo(name = "aperture_cam_exif")
    val aperture: String? = null,

    @ColumnInfo(name = "focal_length_cam_exif")
    val focal_length: String? = null,

    @ColumnInfo(name = "iso_cam_exif")
    val iso: String? = null,
)