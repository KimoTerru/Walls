package it.kimoterru.walls.data.remote.models.photo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class UserResponse(

    @ColumnInfo(name = "id_user")
    @SerializedName("id")
    val id: String? = null,

    @ColumnInfo(name = "updated_at_user")
    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @ColumnInfo(name = "username_user")
    @SerializedName("username")
    val username: String? = null,

    @ColumnInfo(name = "name_user")
    @SerializedName("name")
    val name: String? = null,

    @ColumnInfo(name = "firstName_user")
    @SerializedName("firstName")
    val firstName: String? = null,

    @ColumnInfo(name = "lastName_user")
    @SerializedName("lastName")
    val lastName: String? = null,

    @Embedded
    @SerializedName("profile_image")
    val profileImage: ProfileImageResponse? = null,

    @ColumnInfo(name = "portfolio_url_user")
    @SerializedName("portfolio_url")
    val portfolioUrl: String? = null,

    @ColumnInfo(name = "bio_user")
    @SerializedName("bio")
    val bio: String? = null,

    @ColumnInfo(name = "location_user")
    @SerializedName("locationResponse")
    val location: String? = null,

    @ColumnInfo(name = "total_likes_user")
    @SerializedName("total_likes")
    val totalLikes: Int? = null,

    @ColumnInfo(name = "total_photos_user")
    @SerializedName("total_photos")
    val totalPhotos: Int? = null,

    @ColumnInfo(name = "acceptedTos_user")
    @SerializedName("acceptedTos")
    val acceptedTos: Boolean? = null,

    @ColumnInfo(name = "total_collections_user")
    @SerializedName("total_collections")
    val totalCollections: Int? = null,

    @ColumnInfo(name = "instagram_username_user")
    @SerializedName("instagram_username")
    val instagramUsername: String? = null,

    @ColumnInfo(name = "twitter_username_user")
    @SerializedName("twitter_username")
    val twitterUsername: String? = null,

    @Embedded
    @SerializedName("links")
    val links: UserLinksResponse? = null,
)