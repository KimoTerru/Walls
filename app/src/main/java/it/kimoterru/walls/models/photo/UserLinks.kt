package it.kimoterru.walls.models.photo

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class UserLinks(
    @ColumnInfo(name = "html_user_links")
    @SerializedName("html")
    val html: String,

    @ColumnInfo(name = "likes_user_links")
    @SerializedName("likes")
    val likes: String,

    @ColumnInfo(name = "photos_user_links")
    @SerializedName("photos")
    val photos: String,

    @ColumnInfo(name = "portfolio_user_links")
    @SerializedName("portfolio")
    val portfolio: String,

    @ColumnInfo(name = "self_user_links")
    @SerializedName("self")
    val self: String
)