package it.kimoterru.walls.models.photo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class User(
    /*@ColumnInfo(name = "bio_user")
    @SerializedName("bio")
    val bio: String,*/

    @ColumnInfo(name = "id_user")
    @SerializedName("id")
    val id: String,

    /*@ColumnInfo(name = "instagram_username_user")
    @SerializedName("instagram_username")
    val instagramUsername: String,*/

    @Embedded
    @SerializedName("links")
    val links: UserLinks,

    /*@ColumnInfo(name = "location_user")
    @SerializedName("location")
    val location: String? = "",*/

    @ColumnInfo(name = "name_user")
    @SerializedName("name")
    val name: String,

    /*@ColumnInfo(name = "portfolio_url_user")
    @SerializedName("portfolio_url")
    val portfolioUrl: String,*/

    @Embedded
    @SerializedName("profile_image")
    val profileImage: ProfileImage,

    @ColumnInfo(name = "total_collections_user")
    @SerializedName("total_collections")
    val totalCollections: Int,

    @ColumnInfo(name = "total_likes_user")
    @SerializedName("total_likes")
    val totalLikes: Int,

    @ColumnInfo(name = "total_photos_user")
    @SerializedName("total_photos")
    val totalPhotos: Int,

    /*@ColumnInfo(name = "twitter_username_user")
    @SerializedName("twitter_username")
    val twitterUsername: String,*/

    @ColumnInfo(name = "username_user")
    @SerializedName("username")
    val username: String
)