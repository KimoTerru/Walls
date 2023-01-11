package it.kimoterru.walls.domain.models.photo

data class PhotoUrls(
    val raw: String? = null, // ORIGINAL
    val full: String? = null, // JPG
    val regular: String? = null, //1080
    val small: String? = null, //400
    val thumb: String? = null //200
)