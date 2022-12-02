package it.kimoterru.walls.data.remote.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class UserLinksResponse(

    @ColumnInfo(name = "self_user_links")
    @SerializedName("self")
    val self: String? = null,

    @ColumnInfo(name = "html_user_links")
    @SerializedName("html")
    val html: String? = null,

    @ColumnInfo(name = "photos_user_links")
    @SerializedName("photos")
    val photos: String? = null,

    @ColumnInfo(name = "likes_user_links")
    @SerializedName("likes")
    val likes: String? = null,

    @ColumnInfo(name = "portfolio_user_links")
    @SerializedName("portfolio")
    val portfolio: String? = null,

    @ColumnInfo(name = "following_user_links")
    @SerializedName("following")
    val following: String? = null,

    @ColumnInfo(name = "followers_user_links")
    @SerializedName("followers")
    val followers: String? = null,
)