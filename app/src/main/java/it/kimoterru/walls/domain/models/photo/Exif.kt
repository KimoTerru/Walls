package it.kimoterru.walls.domain.models.photo

data class Exif(
    val make: String? = null,
    val model: String? = null,
    val name: String? = null,
    val exposure_time: String? = null,
    val aperture: String? = null,
    val focal_length: String? = null,
    val iso: String? = null,
)