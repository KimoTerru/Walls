package it.kimoterru.walls.data.remote.models.photo

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("firstName")
    val firstName: String? = null,

    @SerializedName("lastName")
    val lastName: String? = null,

    @SerializedName("profile_image")
    val profileImage: ProfileImageResponse? = null,

    @SerializedName("portfolio_url")
    val portfolioUrl: String? = null,

    @SerializedName("bio")
    val bio: String? = null,

    @SerializedName("locationResponse")
    val location: String? = null,

    @SerializedName("total_likes")
    val totalLikes: Int? = null,

    @SerializedName("total_photos")
    val totalPhotos: Int? = null,

    @SerializedName("acceptedTos")
    val acceptedTos: Boolean? = null,

    @SerializedName("total_collections")
    val totalCollections: Int? = null,

    @SerializedName("instagram_username")
    val instagramUsername: String? = null,

    @SerializedName("twitter_username")
    val twitterUsername: String? = null,

    @SerializedName("links")
    val links: UserLinksResponse? = null,
)