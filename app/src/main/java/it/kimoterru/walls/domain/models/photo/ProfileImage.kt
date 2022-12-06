package it.kimoterru.walls.domain.models.photo

data class ProfileImage(
    val large: String? = null, //128x128
    val medium: String? = null, //64x64
    val small: String? = null //32x32
)