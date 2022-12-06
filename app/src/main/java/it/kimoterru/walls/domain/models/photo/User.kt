package it.kimoterru.walls.domain.models.photo

data class User(
    val id: String? = null,
    val updatedAt: String? = null,
    val username: String? = null,
    val name: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val profileImage: ProfileImage? = null,
    val portfolioUrl: String? = null,
    val bio: String? = null,
    val location: String? = null,
    val totalLikes: Int? = null,
    val totalPhotos: Int? = null,
    val acceptedTos: Boolean? = null,
    val totalCollections: Int? = null,
    val instagramUsername: String? = null,
    val twitterUsername: String? = null,
    val links: UserLinks? = null,
)