package it.kimoterru.walls.data.local.model.photo

import androidx.room.ColumnInfo

data class UserLinksEntity(

    @ColumnInfo(name = "self_user_links")
    val self: String? = null,

    @ColumnInfo(name = "html_user_links")
    val html: String? = null,

    @ColumnInfo(name = "photos_user_links")
    val photos: String? = null,

    @ColumnInfo(name = "likes_user_links")
    val likes: String? = null,

    @ColumnInfo(name = "portfolio_user_links")
    val portfolio: String? = null,

    @ColumnInfo(name = "following_user_links")
    val following: String? = null,

    @ColumnInfo(name = "followers_user_links")
    val followers: String? = null,
)