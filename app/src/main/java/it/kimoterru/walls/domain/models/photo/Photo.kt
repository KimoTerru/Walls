package it.kimoterru.walls.domain.models.photo

data class Photo(
    val id: String? = null,
    var id_photo_is_local: Int,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val color: String? = null,
    val downloads: Int? = null,
    val blurHash: String? = null,
    val likes: Int? = null,
    val likedByUser: Boolean? = null,
    val publicDomain: Boolean? = null,
    val description: String? = null,
    val altDescription: String? = null,
    val exif: Exif? = null,
    val location: Location? = null,
    /*val tags: List<TagsResponse>? = null,*/
    /*val currentUserCollections: List<UserCollection>,*/
    val urls: PhotoUrls? = null,
    val links: PhotoLinks? = null,
    val user: User? = null,
)